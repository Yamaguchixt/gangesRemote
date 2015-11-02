window.onload = function(){
$(function(){
  game = new Game(WIDTH,HEIGHT);
  var initialX = 1; //初期座標
  var initialY = 1;
  game.fps = 16;
  game.preload("/ganges/public/images/chara0.png","/ganges/public/images/map1.png","/ganges/public/images/chara2.png");
  game.onload = function(){
	  global.setScene(initialX,initialY,game.rootScene);
	  global.chara = createPlayer();
	  var map = createMap(initialX,initialY);
	  global.currentMap = map;
	  global.currentMap.x = initialX;
	  global.currentMap.y = initialY;

	  game.rootScene.on('touchstart',function(e){//画面タッチしたらそこに瞬時に移動させる
		global.chara.x = e.x;
		global.chara.y = e.y;
	  });

	  global.label.onenterframe = function(){
		  global.label.text = "x:"+global.chara.x+ ", y: "+global.chara.y + "   /MAP座標[x: "+global.currentMap.x+" y:"+global.currentMap.y+"]";
	  }
	  global.mapChangeManager.addEventListener('enterframe', function(){
		if(global.chara.x > WIDTH || global.chara.x < 0 || global.chara.y > HEIGHT || global.chara.y < 0){//画面端に触れたら
			changeMap(initialX,initialY);
			this.removeEventListener('enterframe',arguments.callee);
		}
	  });
	 // global.scene[initialX+":"+initialY].scene.addChild(map);
	  //global.scene[initialX+":"+initialY].scene.addChild(global.chara);
	  //global.scene[initialX+":"+initialY].scene.addChild(global.mapChangeManager);
	  //global.scene[initialX+":"+initialY].scene.addChild(global.label);
  };//game.onload
  game.start();

});//$(function()
};//window.onload

