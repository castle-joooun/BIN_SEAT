package com.empty.store.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreSalesViewServlet
 */
@WebServlet("/store/salesView")
public class StoreSalesViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreSalesViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dateYoil;
		String date;
		String yoil;
		String minusDate;
		String plusDate;
        
		try {
			Calendar cal = Calendar.getInstance();
			date = request.getParameter("date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(date);
			cal.setTime(d);
			cal.add(Calendar.DATE, 1);
			plusDate = sdf.format(cal.getTime());
			cal.add(Calendar.DATE, -2);
			minusDate = sdf.format(cal.getTime());
			
			
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
			dateYoil = sdf.format(d);
			sdf = new SimpleDateFormat("E");
			yoil = sdf.format(d);
			
			
			
		}catch(Exception e) {
			Calendar cal = Calendar.getInstance();
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(d);
			cal.add(Calendar.DATE, 1);
			plusDate = sdf.format(cal.getTime());
			cal.add(Calendar.DATE, -2);
			minusDate = sdf.format(cal.getTime());
			
			sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
			dateYoil = sdf.format(d);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(d);
			sdf = new SimpleDateFormat("E");
			yoil = sdf.format(d);
			
		}
		
		
		request.setAttribute("dateYoil", dateYoil);
		request.setAttribute("date", date);
		request.setAttribute("yoil", yoil);
		request.setAttribute("plusDate", plusDate);
		request.setAttribute("minusDate", minusDate);
		request.getRequestDispatcher("/views/storesales/salesmain.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
