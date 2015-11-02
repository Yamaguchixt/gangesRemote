enchant();
var global = {
		isSee:true,
		scene: {},
		currentMap:{},
		serverPath:'http://localhost:8080',
		chara: {},
		label: new Label(),
		mapChangeManager: new Sprite(1,1),

		setScene: function(x,y,scene){
			if(!existy(this.scene[x+":"+y])){this.scene[x+":"+y] = {}; }
			this.scene[x+":"+y].scene = scene;
		},
		getScene: function(x,y){
			return this.scene[x+":"+y].scene;
		},
		setShopList: function(x,y,json){
			if(!existy(this.scene[x+":"+y])){ this.scene[x+":"+y] = {};}
			this.scene[x+":"+y].shopList = json;
		},
		getShopList: function(x,y){
			return global.scene[x+":"+y].shopList;
		}
};

function existy(x){return x != null }; // nullとundefinedでないことを保証

var properties = function(obj){
	for(var i in obj){
		console.log(i);
		Object.keys(i).forEach(function(p){
			console.log(p);
		});
	}
}

var log = function(str){
	console.log(str);
}