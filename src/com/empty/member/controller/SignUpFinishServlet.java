package com.empty.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.Member;

@WebServlet(name="SignUpFinishServlet", urlPatterns = "/signUpFinish")
public class SignUpFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpFinishServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("id");
		String password = request.getParameter("psw");
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address1") + " " + request.getParameter("address2");
		String gender = request.getParameter("gender");
		Member m = new Member(userId , password, userName, email, phone, address, gender);
		int result = new MemberService().insertMember(m);
		String msg = ""; 
		String loc = ""; 
		
		if(result > 0) {
			msg = "회원가입이 완료되었습니다.";
			loc = "/";
		}else {
			msg = "회원가입에 실패하였습니다.";
			loc = "/signUp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher rd = request.getRequestDispatcher("views/common/msg.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
