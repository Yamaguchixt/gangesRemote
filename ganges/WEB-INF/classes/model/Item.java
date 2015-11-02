package model;

import org.json.simple.JSONObject;
public class Item implements Jsonable{
	public String shopId;
	public String itemId;
	public String name;
	public String imagePath;
	public int price;

	public JSONObject toJson(){
		JSONObject obj = new JSONObject();
		obj.put("shopId",this.shopId);
		obj.put("itemId", this.itemId);
		obj.put("name", this.name);
		obj.put("imagePath", this.imagePath);
		obj.put("price", new Integer(this.price));
		return obj;
	}
}
