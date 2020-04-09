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
 * Servlet implementation class CrystalcomServlet
 */
@WebServlet("/mypage/crystalcom.do")
public class CrystalcomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrystalcomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member m = new Member();
		String msg="";
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		m.setUserId(request.getParameter("userId"));
		m.setPassword(request.getParameter("password"));
		m.setAddress(request.getParameter("address"));
		m.setBank(request.getParameter("bank"));
		m.setBankNumber(request.getParameter("bankNumber"));
		m.setBankMaster(request.getParameter("bankMaster"));
		
		int result = new MemberService().updateMember(m);
		if(result>0) {
			msg="수정에 성공하였습니다.";
		}else {
			msg="수정 실패";
		}
		jsonObj.put("msg",msg);
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
