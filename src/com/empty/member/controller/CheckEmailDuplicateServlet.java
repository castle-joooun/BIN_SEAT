package com.empty.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.member.model.service.MemberService;

@WebServlet("/checkEmailDupplicate")
public class CheckEmailDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmailDuplicateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		boolean isUseable = new MemberService().selectCheckEmail(email);
        JSONObject obj = new JSONObject();
        try{
            obj.put("isUseable", isUseable);
        }catch (Exception e) {    
        	e.printStackTrace();
        }
		request.setAttribute("isUseable", isUseable);
		response.setContentType("application/json");
        response.getWriter().write(obj.toString());
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
