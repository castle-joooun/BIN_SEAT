package com.empty.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.comment.model.service.CommentService;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/comment/storeCommentDelete")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String commentWriter = (request.getParameter("commentWriter"));
	
	int result=new CommentService().deleteComment(commentWriter);
	 String msg=result>0?"댓글삭제 성공! " : "댓글삭제 실패";
	 String loc="/store?storeId=ooze&storeName=퓨리&searchText=강남";
	 
	 request.setAttribute("msg", msg);
	 request.setAttribute("loc", loc);
	 
	 System.out.println("삭제" + commentWriter);
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
