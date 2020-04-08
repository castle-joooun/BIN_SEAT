package com.empty.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.comment.model.service.CommentService;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/comment/storeCommentUpdateEnd")
public class UpdateCommentEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 int no =Integer.parseInt(request.getParameter("commentNo"));
	 String userComment=request.getParameter("userComment");
	 

	 
	 int result=new CommentService().updateComment(no,userComment);
	 
	 String msg=result>0?"댓글수정 성공! " : "댓글수정 실패";
	 String loc="/store";
	 
	 request.setAttribute("msg", msg);
	 request.setAttribute("loc", loc);

	 request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	 
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
