package com.empty.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

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
		String msg = "";
		HttpSession session = request.getSession();
		
		if(m != null && m.getStatus().equals("Y")) {
			session.setAttribute("loginMember", m);
			Cookie mem = new Cookie("loginMember", userId);
			mem.setMaxAge(7*24*60*60);
			response.addCookie(mem);
			String saveId = request.getParameter("saveId");
			if(saveId != null) {
				Cookie c = new Cookie("saveId", userId);
				c.setMaxAge(7*24*60*60);
				response.addCookie(c);
			}else {
				Cookie c = new Cookie("saveId", userId);
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}else {
			msg = URLEncoder.encode("입력하신 정보가 없습니다.", "UTF-8");
			Cookie cookies = new Cookie("loginFail", msg);
			cookies.setMaxAge(60);
			response.addCookie(cookies);
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
