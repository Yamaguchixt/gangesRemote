package controller.manage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Map;
import util.Util;
import DAO.MapDAO;

/**
 * Servlet implementation class EnchantManageController
 */
@WebServlet("/EnchantManageController")
public class EnchantManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EnchantManageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String nextPath = "";

		if(action.equals("mapCreate")){
			Map map = new Map();
			MapDAO mapDao = new MapDAO();
			//setPointメソッド内でmap.x map.yの代入をしているのでpointはsetで格納する。他のsetメソッドも作る。
			String mapPoint = request.getParameter("mapPoint");
			int x = Integer.parseInt(mapPoint.split(".")[0]);
			int y = Integer.parseInt(mapPoint.split(".")[1]);
			String drawData = request.getParameter("drawData");
			String objectData = request.getParameter("objectData");
			String collisionData = request.getParameter("collisionData");
			String imagePath = request.getParameter("imagePath");

			//とりあえず入力不正でもエラーださない処理、適宜はじく処理が必要。
			map.x = x;
			map.y = y;
			map.drawData = Util.isValid(drawData, "int[][]") ? drawData:"[[0]]";
			map.objectData = Util.isValid(objectData,"int[][]") ? objectData:"[[0]]";
			map.collisionData = Util.isValid(collisionData,"int[][]") ? collisionData:"[[0]]";
			map.imagePath = Util.isValid(imagePath,"String") ? imagePath:"public/images/map1.png";

			mapDao.create(map);
			nextPath = "/jsp/manage/formMapData.jsp";
		}

		request.getRequestDispatcher(nextPath).forward(request,response);
	}

}
