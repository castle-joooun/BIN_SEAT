package com.empty.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.comment.model.service.CommentService;
import com.empty.comment.model.vo.Comment;

/**
 * Servlet implementation class StoreCommentInsert
 */
@WebServlet("/comment/storeCommentInsert")
public class StoreCommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreCommentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String commentWriter=request.getParameter("commentWriter");
		String userComment=request.getParameter("userComment");
		int commentLevel=Integer.parseInt(request.getParameter("commentLevel"));
	
		System.out.println("1 :"+commentWriter +"2: "+userComment+"3:"+commentLevel);
		Comment c = new Comment(0,commentLevel,commentWriter,userComment,null);
	
		System.out.println(userComment);
		int result =new CommentService().insertComment(c);
	
		
		String msg=result>0?"댓글등록 성공! " : "댓글 등록 실패";
		String loc="/store?storeId=ooze&storeName=퓨리&searchText=강남";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		System.out.println("1 :"+commentWriter +"2: "+userComment+"3:"+commentLevel);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
