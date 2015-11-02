package util;

import java.util.ArrayList;
import java.util.List;





public class Test {
	public static void main(String[] args) throws Exception {
		List<String> cities = new ArrayList<String>();
		cities.add("aaa");
		cities.add("bbb");
		cities.add("ccc");
		cities.forEach((final String city) -> System.out.println(city));


	}
}
