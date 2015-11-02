var goPurchase = function(shopId){

	//shopIdから商品リスト取得
	var path = global.serverpath + "/ganges/EnchantApi?action=getItems&shopId="+shopId;
	$.getJSON(path,function(json){

	}

	//Sceneを作成
	//Sceneに商品追加
}