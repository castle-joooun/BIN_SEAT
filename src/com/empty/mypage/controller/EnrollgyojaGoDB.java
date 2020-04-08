package com.empty.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class EnrollgyojaGoDB
 */
@WebServlet("/mypage/enrollgyojaGoDB")
public class EnrollgyojaGoDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollgyojaGoDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String bankNumber = request.getParameter("gyojanumber");
		String bankMaster = request.getParameter("bankMaster");
		String bank = request.getParameter("bank");
		String msg="";
		int result = new MemberService().updateBank(userId, bankNumber, bankMaster, bank);
		if(result>0) {
			msg="계좌등록이 되었습니다.";
		}else {
			msg="계좌등록에 실패하였습니다.";
		}
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("msg", msg);
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
