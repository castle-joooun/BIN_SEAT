package com.empty.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.InputMoneyDB;
import com.empty.member.model.vo.Member;

/**
 * Servlet implementation class ImListServlet
 */
@WebServlet("/use/imList")
public class ImListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		String userId= m.getUserId();
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerPage=5;
		List<InputMoneyDB> list=new MemberService().selectUseList2(userId,cPage,numPerPage);
		int totalBoard=new MemberService().useListCount2(userId);
		m.setCash(list.get(0).getAfterIm());
		int totalPage=(int)Math.ceil((double)totalBoard/numPerPage);
		
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		String pageBar="";
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/use/imList?cPage="+(pageNo-1)+"'>[이전]</a>";					
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/use/imList?cPage="+(pageNo)+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/use/imList?cPage="+(pageNo)+"'>[다음]</a>";					
		}

		request.setAttribute("list",list);
		request.setAttribute("pageBar2", pageBar);
		request.getRequestDispatcher("/views/mypage/imList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
