package com.empty.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.event.model.vo.Event;
import com.empty.event.service.EventService;
import com.empty.search.model.vo.Store;
import com.empty.search.service.SearchService;

@WebServlet("/event/eventView")
public class EventViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		Cookie[] cookies = request.getCookies();
		String cookieVal = "";
		boolean hasRead = false;
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				if("EventCookie".equals(name)) {
					cookieVal = value;
					if(value.contains("|" + no + "|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		
		if(!hasRead) {
			Cookie c = new Cookie("EventCookie",cookieVal + "|" + no + "|");
			c.setMaxAge(-1);
			response.addCookie(c);
		}
		
		Event e = new EventService().selectEvent(no,hasRead);
		request.setAttribute("Event", e);
		Store s = new SearchService().searchName(e.getEventWriter());
		System.out.println(e.getEventWriter());
		request.setAttribute("store", s);
		System.out.println(s);
		
		
		request.getRequestDispatcher("/views/event/eventView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
