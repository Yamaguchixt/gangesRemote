package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Config {

	//イニシャライザ。クラスロード時に一度だけよばれる。
	static {
		try{
			System.out.println(" static initialize invoked");
			Properties properties = new Properties();
			InputStream is = Config.class.getResourceAsStream("ganges.properties");
			properties.load(is);
			driverName = properties.getProperty("driverName");
			connection = properties.getProperty("connection");
			user       = properties.getProperty("user");
			pass       = properties.getProperty("pass");
			serverURL  = properties.getProperty("serverURL");

		} catch (IOException e) {
			e.printStackTrace();
		} finally{

		}
	}
	public static  String driverName;
			//"com.mysql.jdbc.Driver";
	//public static String driverName = "org.mariadb.jdbc.Driver";

	public static  String connection;
	//="jdbc:mysql://localhost/ganges?characterEncoding=utf8";
	//public static final String connection = "jdbc:mariadb://localhost:3306/ganges";

	//DBで使うusernameとpass
	public static  String user;
	//="root";
	public static  String pass;
	//="";

	public static  String serverURL;
	//= "http://localhost:8080/ganges/";
	//public static final String serverURL = "http://hew2015.com/tomcat/ganges/";

}
