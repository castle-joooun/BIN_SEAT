package com.empty.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.event.model.vo.Event;
import com.empty.event.service.EventService;
import com.empty.notice.model.vo.Notice;

@WebServlet("/eventWriteEnd")
public class EventWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EventWriteEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String storeName = new EventService().selectStoreName(writer);
		Event e = new Event(0, title, storeName, content, null, 0);
		
		int result = new EventService().insertEvent(e);
		String msg = "";
		String loc = "";
		if(result > 0) {
			msg = "게시물 등록 완료";
			loc = "/event";
		}else {
			msg = "게시물 등록 실패";
			loc = "/event";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
