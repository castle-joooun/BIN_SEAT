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

@WebServlet(name = "UpdatePasswordServlet", urlPatterns = "/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("id");
		String password = request.getParameter("psw");
		
		Member m = new Member(userId, password);
		int result = new MemberService().updatePassword(m);

		String msg = ""; 
		String loc = ""; 
		if(result > 0) {
			msg = "��й�ȣ�� ����Ǿ����ϴ�.";
			loc = "/";
		}else {
			msg = "��й�ȣ ���濡 �����Ͽ����ϴ�.";
			loc = "/";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher rd = request.getRequestDispatcher("views/common/msg.jsp");
		rd.forward(request, response);
		System.out.println(m);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
