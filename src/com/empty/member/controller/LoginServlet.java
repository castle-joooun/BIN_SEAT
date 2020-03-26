package com.empty.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.Member;

@WebServlet(name = "LoginServlet" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("uname");
		String password = request.getParameter("psw");
		Member m = new MemberService().login(userId, password);
		System.out.println(m);
		String msg = "";
		
		if(m != null) {
			msg = "�α��� ����";
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", m);
			Cookie mem = new Cookie("loginMember", userId);
			mem.setMaxAge(7*24*60*60);
			response.addCookie(mem);
			String saveId = request.getParameter("saveId");
			System.out.println("saveId : " + saveId);
			if(saveId != null) {
				// ���̵� ��Ű�� �����ϰ���
				Cookie c = new Cookie("saveId", userId);
				//��Ű�� ��ȿ�Ⱓ ����
				c.setMaxAge(7*24*60*60);
				response.addCookie(c);
			}else {
				// ����� cookie�� ����� check�Ȱ� ����
				Cookie c = new Cookie("saveId", userId);
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}else {
			msg = "�α��� ����";
		}
		
		request.setAttribute("msg", msg);
		String loc="/";
		request.setAttribute("loc",loc);
//		RequestDispatcher rs = request.getRequestDispatcher("/views/common/msg.jsp");
//		rs.forward(request, response);
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
