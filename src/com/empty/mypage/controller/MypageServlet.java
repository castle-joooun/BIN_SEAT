package com.empty.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.cash.model.service.VinService;
import com.empty.member.model.vo.Member;
import com.empty.mypage.model.vo.InputMoneyList;
import com.empty.search.model.vo.Store;
import com.empty.search.service.SearchService;
import com.google.gson.Gson;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		Member m = new Member();
		Store s= new SearchService().crystalstore(userId);
		if(s.getStoreName()!=null) {
			InputMoneyList ipml = new InputMoneyList();
			m=new SearchService().storemoney(userId);
		}else {
			m = new VinService().selectUser(m,userId);
		}
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("cash",m.getCash());
		jsonObj.put("userId",m.getUserId());
		jsonObj.put("email",m.getEmail());
		jsonObj.put("phone",m.getPhone());
		jsonObj.put("address",m.getAddress());
		jsonObj.put("gender",m.getGender());
		jsonObj.put("enrolldate",m.getEnrollDate());
		jsonObj.put("bank",m.getBank());
		jsonObj.put("bankNumber",m.getBankNumber());
		jsonObj.put("bankMaster",m.getBankMaster());
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
