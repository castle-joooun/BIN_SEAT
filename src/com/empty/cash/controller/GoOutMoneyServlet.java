package com.empty.cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.cash.model.service.VinService;
import com.empty.member.model.vo.Member;
import com.empty.member.model.vo.outMoneyDB;
import com.google.gson.Gson;

/**
 * Servlet implementation class CrystalcomServlet
 */
@WebServlet("/cash/goOutMoneySV.do")
public class GoOutMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoOutMoneyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member m = new Member();
		int money = Integer.parseInt(request.getParameter("money"));
		String userId = request.getParameter("userId");
		String msg = "";
		int flag = 0;
		m = new VinService().selectUser(m, userId);	   //내정보불러오기
		if(m.getCash()<money) {
			msg="출금할 잔액부족합니다.";
			flag=0;
		}else if(money<=0 && money <999) {
			msg="최소 출금금액보다 작습니다.";
			flag=0;
		}else{
			new VinService().minusCash(m, money);    //내 db계정 캐시 감소
			new VinService().outMoneyListDB(m, money);  //출금내역 db에 저장
			msg="출금이 되었습니다.";
			flag=1;
		}
		
//		request.setAttribute("msg", msg);
//		request.setAttribute("loc", msg);
//		request.getRequestDispatcher(loc).forward(request, response);
		
		
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("msg", msg);
		jsonObj.put("flag", flag);
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
