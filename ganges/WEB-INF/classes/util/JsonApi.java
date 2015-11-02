package util;
import java.util.Iterator;
import java.util.List;

import model.Jsonable;

import org.json.simple.JSONArray;

public class JsonApi {
	@SuppressWarnings("unchecked")
	public static JSONArray intArrayToJSONArray(int[] array){
		JSONArray jsonArray = new JSONArray();
		for(int j=0;j<array.length;j++){
			jsonArray.add(new Integer(array[j]));
		}
		return jsonArray;
	}
	@SuppressWarnings("unchecked")
	public static JSONArray int2DArrayToJSONArray(int[][] array){
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i < array.length; i++){
			jsonArray.add(intArrayToJSONArray(array[i]));
		}
		return jsonArray;
	}

	public static <T extends Jsonable> JSONArray ListToJSON(List<T> list){
		JSONArray array = new JSONArray();
		Iterator<T> ite = list.iterator();//Itemハードコードなんとかしたい
		while(ite.hasNext()){
			array.add(ite.next().toJson());//T型はJsonableなのでtoJsonよべる
		}
		return array;
	}
}
