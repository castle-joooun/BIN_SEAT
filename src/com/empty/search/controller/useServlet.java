package com.empty.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.cash.model.service.VinService;
import com.empty.member.model.vo.Member;
import com.empty.member.model.vo.outMoneyDB;
import com.empty.search.service.SearchService;
import com.google.gson.Gson;

/**
 * Servlet implementation class useServlet
 */
@WebServlet("/use/use.do")
public class useServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public useServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		Member m = new Member();
		outMoneyDB omdb = new outMoneyDB();
		m = new VinService().selectUser(m,userId);
		//List list =new SearchService().outMoneyList(userId, omdb); 
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=5;
		List list2=new SearchService().outMoneyList(userId, omdb,cPage,numPerPage);
		int totalBoard=new SearchService().omlCount();
		
		int totalPage=(int)Math.ceil((double)totalBoard/numPerPage);
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/use/use.do?cPage="+(pageNo-1)+"&userId=" + userId + "'>[이전]</a>";					
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/use/use.do?cPage="+(pageNo)+"&userId=" + userId + "'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/use/use.do?cPage="+(pageNo)+"'>[다음]</a>";					
		}
			
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("list", list2);
		jsonObj.put("pageBar", pageBar);
		
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(jsonObj,response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
