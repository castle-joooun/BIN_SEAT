package com.empty.search.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.search.model.vo.Store;
import com.empty.search.service.SearchService;
import com.google.gson.Gson;

/**
 * Servlet implementation class StroeFavoriteServlet
 */
@WebServlet("/favorite")
public class StoreFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String check = request.getParameter("check");
		String userId = request.getParameter("userId");
		String storeId = request.getParameter("storeId");
		Store store = new SearchService().store(storeId);
		
		// store_id, store_name, user_id, check(true, false) 다 있음!
		
		int result = 0;
//		

		
		// 즐겨찾기 갯수 제한하기
		int favoriteSize = new SearchService().favoriteSize(userId);
		
		if(favoriteSize>=6) {
			
			
		} else {
			
			if(check.equals("true")) {
				result = new SearchService().storeFavoriteInsert(userId, store.getStoreLogo(), storeId, store.getStoreName());
			} else if(check.equals("false")){
				result = new SearchService().storeFavoriteDelete(userId, storeId);
			}
			
			
			if(result>0) {
			} else {
			}
		}
		
		JSONObject jo = new JSONObject();
		jo.put("result", favoriteSize);
		new Gson().toJson(favoriteSize,response.getWriter());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
