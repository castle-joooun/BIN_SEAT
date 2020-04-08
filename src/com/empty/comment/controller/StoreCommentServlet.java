package com.empty.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.comment.model.service.CommentService;
import com.empty.comment.model.vo.Comment;
import com.empty.search.model.vo.Store;
import com.empty.search.model.vo.StoreSeat;
import com.empty.search.service.SearchService;

/**
 * Servlet implementation class StoreCommentServlet
 */
@WebServlet("/StoreCommentServlet")
public class StoreCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		int cPage;
//		try {
//			cPage=Integer.parseInt(request.getParameter("cPage"));
//			
//		}catch(NumberFormatException e) {
//			cPage=1;
//		}
//		int numPerPage=5;
//		try {
//			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
//		}catch(NumberFormatException e) {
//			numPerPage=5;
//		
//		}
//		List<Comment> list = new CommentService().searchComment(cPage,numPerPage);
//		int totalStore=new CommentService().commentCount();
//		int totalPage=(int)Math.ceil((double)totalStore/numPerPage);
//		
//		//페이지 바 만들기 
//		String pageBar="";
//		int pageBarSize=5;
//		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
//		int pageEnd=pageNo+pageBarSize-1;
//		
//		if(pageNo==1) {
//		 	pageBar+="<span>[이전]</span>";
//		 	
//	}else {
//		pageBar+="<a href='"+request.getContextPath()+"/StoreCommentServlet?cPage="+(pageNo-1)+"'>[이전]</a>";
//				
//	}
//	
//		while(!(pageNo>pageEnd||pageNo>totalPage)) {
//			if(pageNo==cPage) {
//				pageBar+="<span>"+pageNo+"</span>";
//			}else {
//					pageBar+="<a href='"+request.getContextPath()+"/StoreCommentServlet?cPage="+(pageNo)+"'>"+pageNo+"</a>";
//					
//			}
//			pageNo++;
//		}
//		
//		//다음
//		if(pageNo>totalPage) {
//			pageBar+="<span>"+pageNo+"</span>";
//		}else {
//			pageBar+="<a href='"+request.getContextPath()+"/StoreCommentServlet  ?cPage="+(pageNo)+"'>[다음]</a>";
//		}
//
//		
//	
//		
//		request.setAttribute("pageBar", pageBar);
//		request.setAttribute("list", list);
//	
//		
//		request.getRequestDispatcher("/views/search/store.jsp").forward(request, response);
	
	}
	





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
