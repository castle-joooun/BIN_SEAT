package com.empty.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.empty.member.model.vo.Member;
import com.empty.search.model.vo.Store;
import com.empty.store.model.service.StoreService;

/**
 * Servlet Filter implementation class StoreFilter
 */
@WebFilter("/store/*")
public class StoreFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StoreFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session =  ((HttpServletRequest)request).getSession(false);
		Member m =(Member)session.getAttribute("loginMember");
		String id;
		Store s=null;
		try{
			id = m.getUserId();
			s = new StoreService().selectStore(id);
		}catch(Exception e) {
			id=null;
		}
		
			
		if(id==null||s==null) {
			request.setAttribute("msg", "잘못된 접근입니다. 메인페이지로 이동합니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
		}else {
			chain.doFilter(request, response);
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
