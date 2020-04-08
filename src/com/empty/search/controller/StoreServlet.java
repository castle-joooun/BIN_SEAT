package com.empty.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.comment.model.service.CommentService;
import com.empty.comment.model.vo.Comment;

import com.empty.reservation.service.ReservationService;

import com.empty.search.model.vo.Store;
import com.empty.search.model.vo.StoreSeat;
import com.empty.search.service.SearchService;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/storeView")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	//댓글 페이징	
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
			
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		
		int numPerPage=5;
		
		try {
			cPage=Integer.parseInt(request.getParameter("numPerPage"));
			
		}catch(NumberFormatException e) {
			 numPerPage=5;
		}
//		List<Comment> commentList= new CommentService().selectComment();
		List<Comment> commentList = new CommentService().searchComment(cPage, numPerPage);
		int totalStore=new CommentService().commentCount();
		int totalPage=(int)Math.ceil((double)totalStore/numPerPage);
		
		//페이지 바 만들기 
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1) {
		 	pageBar+="<span>[이전]</span>";
		 	
	}else {
		pageBar+="<a href='"+request.getContextPath()+"/store?cPage="+(pageNo-1)+"'>[이전]</a>";
				
	}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
					pageBar+="<a href='"+request.getContextPath()+"/store?cPage="+(pageNo)+"'>"+pageNo+"</a>";
					
			}
			pageNo++;
		}
		
		//다음
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/store?cPage="+(pageNo)+"'>[다음]</a>";
		}
//		request.setAttribute("commentList", commentList);

		
//댓글 페이징	
		String storeId = request.getParameter("storeId");
		String userId = request.getParameter("userId");
		Store store = new SearchService().store(storeId);
		String searchText = request.getParameter("searchText");
		
		// getParameter로 받는거 3개.
		// storeId, userId, searchText
		List<String> imgs = new SearchService().storeImgs(storeId);
		
		long now = System.currentTimeMillis();

		// 시간의 차이가 -거나 0인것들은 emptySeat로 돌리기 위해
		// store_seat_check에서 seat_yn의 값을 0으로 수정.
		// 1. 받아오기
		List<String> checkYN = new ReservationService().checkYN(storeId, now);
		if (checkYN.size() != 0) {
		}

		// 2-1. store_seat_check에 값 바꿔주기.
		int changeYN1 = new ReservationService().changeYN1(storeId, checkYN);
		if (changeYN1 > 0) {
		}

		// 2-2.
		// store_seat seat_check 불러오기
		String seats = new ReservationService().seatList(storeId);
		if (seats != null) {
		}

		String[] list = seats.split(",");
		
		for(int i=0; i<checkYN.size(); i++) {
			int seatNum = Integer.parseInt(checkYN.get(i));
			list[seatNum - 1] = "0";
		}
		String tranSeats = "";

		for (int i = 0; i < list.length; i++) {
			if (i == list.length - 1) {
				tranSeats += list[i];
			} else {
				tranSeats += list[i] + ",";
			}
		}


		// store_seat seat_check 반환하기 (1=사용중)
		int useSeat = new ReservationService().inputSeat(storeId, tranSeats);
		if (useSeat > 0) {
		}
		
		StoreSeat ss = new SearchService().storeSeat(storeId);
		
		// 즐겨찾기 되어 있는지 확인
		String favoriteUrl = userId.equals("")?"image/favorite-empty.png":new SearchService().storeFavoriteCheck(userId, storeId);
		if(favoriteUrl.equals("image/favorite-use.png")) {
		} else {
		}
		
		if (store != null && ss != null) {
			request.setAttribute("store", store);
			request.setAttribute("imgs", imgs);
			request.setAttribute("storeSeat", ss);
			request.setAttribute("searchText", searchText);
			request.setAttribute("url", favoriteUrl);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("commentList", commentList);

			request.getRequestDispatcher("/views/search/store.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/search/noneSearch.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
