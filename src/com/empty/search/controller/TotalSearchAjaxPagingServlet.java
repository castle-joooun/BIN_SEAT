package com.empty.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.search.service.SearchService;
import com.google.gson.Gson;

/**
 * Servlet implementation class TotalSearchAjaxPagingServlet
 */
@WebServlet("/totalSearch/ajaxPaging")
public class TotalSearchAjaxPagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalSearchAjaxPagingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cPage=Integer.parseInt(request.getParameter("cPage"));
		int numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		String keyword = request.getParameter("keyword");
		List list = new SearchService().totalSearch(keyword,cPage,numPerPage);
		int totalData = new SearchService().dataCount(keyword);
		int totalPage = (int)Math.ceil((double)totalData/numPerPage);
		String pageBar = "";
		if(list.size()>0) {
			//페이지바 클릭 사용시 활성화 하기
//			if(cPage<totalPage) {
//				pageBar ="<div class='btnContainer'>" + 
//						"<button name='moreSearch' type='button' onclick='requestData(\""+keyword+"\","+(++cPage)+","+numPerPage+");'><img src='/EMPTY/image/underArrow.png' style='cursor: pointer;'> &nbsp;&nbsp;검색결과 더보기( "+ (cPage-1)*numPerPage+" / "+totalData+" )</button>" + 
//						"</div>";
//			}
			list.add(pageBar);
			list.add(totalData);
		}else {
			pageBar="<img src='/EMPTY/image/searchicon.png'style='witdh:25px;height:25px;'><p>&nbsp;&nbsp;검색된 결과가 없습니다.</p>"+
					"<ul>"
					+ "<li>단어의 철자가 정확한지 확인해 보세요.</li>"
					+ "<li>검색어의 단어 수를 줄이거나, 보다 일반적인 검색어로 다시 검색해 보세요.</li>"
					+ "<li>검색 옵션을 변경해서 다시 검색해 보세요.</li>"
					+ "</ul>";
			list.add(pageBar);
		}
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(list,response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
