package com.empty.mypage.controller;

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
 * Servlet implementation class PcDBServlet
 */
@WebServlet("/mypage/pcdb")
public class PcDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PcDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		Store s= new SearchService().crystalstore(userId);
		String storeTimese []= new String[2];
		String w = "";
		storeTimese= s.getStoreTime().split(" ~ ");
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("storeName", s.getStoreName());
		jsonObj.put("storePhone", s.getStorePhone());
		jsonObj.put("storeTime", s.getStoreTime());
		jsonObj.put("storeInfo", s.getStoreInfo());
		jsonObj.put("storeFacility", s.getStoreFacility());
		jsonObj.put("storeAddress", s.getStoreAddress());
		jsonObj.put("storePrice", s.getStorePrice());
		jsonObj.put("storeTimes", storeTimese[0].toString());
		jsonObj.put("storeTimee", storeTimese[1].toString());
		
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(jsonObj,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
