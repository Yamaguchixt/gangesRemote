var game;
var WIDTH = 784;
var HEIGHT = 640;
var newChara;



var createPlayer = function(){
	var DIR_LEFT  = 0;
	var DIR_RIGHT = 1;
	var DIR_UP    = 2;
	var DIR_DOWN  = 3;
	var player = new Sprite(32,32)
    player.image = game.assets['/ganges/public/images/chara0.png'];
    player.x = 160;
    player.y = 160;
    player.dir   = DIR_DOWN;
    player.anim  = [
         9, 10, 11, 10, //左
        18, 19, 20, 19, //右
        27, 28, 29, 28, //上
         0,  1,  2,  1];//下

    //プレイヤーの定期処理
  player.tick = 0;
    player.addEventListener(Event.ENTER_FRAME, function() {
        //上へ移動
        if (game.input.up) {
            player.dir = DIR_UP;
            player.y -= 4;
            if (global.currentMap.hitTest(player.x + 16, player.y + 32)) player.y += 4;
        }
        //下へ移動
        else if (game.input.down) {
            player.dir = DIR_DOWN;
            player.y += 4;
            if (global.currentMap.hitTest(player.x + 16, player.y + 32)) player.y -= 4;
        }
        //左へ移動
        else if (game.input.left) {
            player.dir = DIR_LEFT;
            player.x -= 4;
            if (global.currentMap.hitTest(player.x + 16, player.y + 32)) player.x += 4;
        }
        //右へ移動
        else if (game.input.right) {
            player.dir = DIR_RIGHT;
            player.x += 4;
            if (global.currentMap.hitTest(player.x + 16, player.y + 32)) player.x -= 4;
        }

        //フレームの指定
        player.tick++;
        if (!game.input.up && !game.input.down &&
            !game.input.left && !game.input.right) player.tick = 1;//静止
        player.frame = player.anim[player.dir * 4 + (player.tick % 4)];

    });
    return player;
};

var createMap = function(x,y){
	var map = new ExMap(16,16);
	var path = "http://localhost:8080/ganges/EnchantApi?action=getMap&x="+x+"&y="+y;
	$.getJSON(path,function(json){
		map.x = json.x;
		map.y = json.y;
		map.image = game.assets[json.imagePath];
		map.loadData(json.mapDrawData,json.mapObjectData);
		map.collisionData = json.mapCollisionData;
		global.setShopList(x,y,JSON.parse(JSON.stringify(json.shopList)));
		createShops(x,y,map);
	});
	return map;
};

var createShops = function(x,y,map){//createMapから呼ぶこと
	global.scene[x+":"+y].scene.addChild(map);
	global.scene[x+":"+y].scene.addChild(global.chara);
	global.scene[x+":"+y].scene.addChild(global.label);
	global.scene[x+":"+y].scene.addChild(global.mapChangeManager);
	var j = 0;//shopのframeに使用
	var array = global.getShopList(x,y);
	for(var i = 0;i < array.length;i++){
		var shop = new Sprite(32,32);
		//shop.image = game.assets[global.json.shopList[i].imagePath]; ひとまず画像path固定
		shop.image = game.assets['/ganges/public/images/chara2.png'];
		shop.frame = j;
		shop.x = array[i].x;
		shop.y = array[i].y;
		shop.Id = array[i].shopId;
		shop.on('enterframe',function(){
			if(global.chara.intersect(this)){
				log("intersect:     "+ this.Id);//なぜかここをshopにするとひとつのshopにしかイベントが設定されない。上書きされてしまう。
				this.removeEventListener('enterframe',arguments.callee);
			}
		});
		global.scene[x+":"+y].scene.addChild(shop);
	}
};

var changeMap = function(x,y){
	if(global.chara.x >= WIDTH){
		global.chara.x = 0;
		global.setScene((x+1),y,new Scene());
		global.currentMap = createMap((global.currentMap.x+1),global.currentMap.y);
		global.currentMap.x = x+1;
		global.currentMap.y = y;
	}
	if(global.chara.x <= -10){
		global.chara.x = WIDTH - 30;
		global.setScene((x-1),y,new Scene());
		global.currentMap = createMap((global.currentMap.x-1),global.currentMap.y);
		global.currentMap.x = x-1;
		global.currentMap.y = y;
	}
	//mapをさきに加える。 chara mapとすると上書きされてしまう

	global.mapChangeManager.addEventListener('enterframe', function(){
		if(global.chara.x > WIDTH || global.chara.x < -10 || global.chara.y > HEIGHT || global.chara.y < 0){//画面端に触れたら
			changeMap(global.currentMap.x,global.currentMap.y);
			this.removeEventListener('enterframe',arguments.callee);
		}
	 });
	global.scene[global.currentMap.x+":"+global.currentMap.y].scene.on('touchstart',function(e){//画面タッチしたらそこに瞬時に移動させる
		global.chara.x = e.x;
		global.chara.y = e.y;
	 });

	//global.scene[x+":"+y].scene.addChild(global.currentMap);
	//global.scene[x+":"+y].scene.addChild(global.label);
	//global.scene[x+":"+y].scene.addChild(global.chara);
	//global.scene[x+":"+y].scene.addChild(global.mapChangeManager);
	game.pushScene(global.scene[global.currentMap.x+":"+global.currentMap.y].scene);
};
