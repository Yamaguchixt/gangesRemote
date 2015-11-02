package model;

import org.json.simple.JSONObject;

public class Shop implements Jsonable {
	public double mapPoint;
	public int x;
	public int y;
	public String shopId;
	public String imagePath;

	public JSONObject toJson(){
		JSONObject obj = new JSONObject();
		//obj.put("mapPoint",new Double(this.mapPoint)); 処理側で今のところつかわない
		obj.put("x", new Integer(this.x));
		obj.put("y", new Integer(this.y));
		obj.put("shopId", this.shopId);
		obj.put("imagePath", this.imagePath);
		return obj;
	}
}
