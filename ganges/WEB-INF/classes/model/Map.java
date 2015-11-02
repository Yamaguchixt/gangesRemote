package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.JsonApi;
import util.Util;

public class Map implements Jsonable {
	public int x;
	public int y;
	public String drawData;
	public String objectData;
	public String collisionData;
	public String imagePath;
	//shop管理用
	public List<Shop> shopList;

	public Map(){
		shopList = new ArrayList<Shop>();
	};
	public String toString(){
		return "x:"+this.x+" y:"+this.y+"\ndrawData:"+this.drawData;
	}

	public void add(Shop shop){
		this.shopList.add(shop);
	}
	//このJsonApiとUtilのメソッドMapクラスのprivatemethodでよくない？ここでしか使ってないし
	@SuppressWarnings("unchecked")
	public JSONObject toJson(){
		JSONObject obj = new JSONObject();
		obj.put("x",new Integer(this.x));
		obj.put("y", new Integer(this.y));
		obj.put("mapDrawData",JsonApi.int2DArrayToJSONArray(Util.mapStringToInt2DArray(this.drawData)));
	   	obj.put("mapObjectData", JsonApi.int2DArrayToJSONArray(Util.mapStringToInt2DArray(this.objectData)));
	   	obj.put("mapCollisionData",JsonApi.int2DArrayToJSONArray(Util.mapStringToInt2DArray(this.collisionData)));
	   	obj.put("imagePath",this.imagePath);

	   	JSONArray array = new JSONArray();
	   	for(Shop i:this.shopList){
	   		array.add(i.toJson());
	   	}
	   	obj.put("shopList", array);

	   	return obj;
	}


}
