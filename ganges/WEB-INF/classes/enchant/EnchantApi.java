package enchant;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;

import org.json.simple.JSONObject;

import DAO.ItemsDAO;
import DAO.MapDAO;

@WebServlet("/EnchantApi")
public class EnchantApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnchantApi() {
        super();
    }

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if(action.equals("getMap")){
			int x = Integer.parseInt(request.getParameter("x"));
			int y = Integer.parseInt(request.getParameter("y"));
		    MapDAO dao = new MapDAO();
		    model.Map map = dao.find(x,y);
		    if(map != null){
		    	JSONObject obj = map.toJson();
		    	obj.writeJSONString(out);
		    }else{
		    	new JSONObject().writeJSONString(out);
		    }
		}

		if(action.equalsIgnoreCase("getItems")){
			String shopId = request.getParameter("shopId");//null checkしてない
			ItemsDAO dao = new ItemsDAO();
			ArrayList<Item> list = dao.find(shopId);
			//ここで一発でJSONにしたい
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
