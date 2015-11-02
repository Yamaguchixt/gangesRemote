package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.Config;

public class ItemsDAO {
	public ArrayList<Item> find(String shopId){
		Logger logger = LoggerFactory.getLogger(ItemsDAO.class);

		try{
			Class.forName(Config.driverName);
		} catch( ClassNotFoundException e){
			System.out.println("driverの読み込み失敗");
			e.printStackTrace();
		}

		ArrayList<Item> list = new ArrayList<Item>();

		try(Connection conn = DriverManager.getConnection(Config.connection,Config.user,Config.pass)){
			String sql = "select * from item where shopId like ?";
			try(PreparedStatement pst = conn.prepareStatement(sql)){
				pst.setString(1,shopId);
				ResultSet rs = pst.executeQuery();
				while(rs.next()){
					Item item = new Item();
					item.shopId = rs.getString("shopId");
					item.itemId = rs.getString("itemId");
					item.name = rs.getString("name");
					item.imagePath = rs.getString("imagePath");
					item.price = rs.getInt("price");
					list.add(item);
				}
			} catch(SQLException e){
				System.out.println("item情報のselectで失敗");e.printStackTrace();
			}

		} catch(Exception e){
			e.printStackTrace();
			//1.7のautoclosableでfinally書かなくていいらしい。
		}
		return list;
	}
}
