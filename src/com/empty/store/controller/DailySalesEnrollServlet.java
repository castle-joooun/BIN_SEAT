package com.empty.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.store.model.service.StoreService;
import com.empty.store.model.vo.StoreSales;

/**
 * Servlet implementation class DailySalesEnrollServlet
 */
@WebServlet("/store/enrollDailySales")
public class DailySalesEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailySalesEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String storeId = request.getParameter("storeId");
		String storeName = request.getParameter("storeName");
		String date = request.getParameter("date");
		String yoil = request.getParameter("yoil");
		int customer = Integer.parseInt(request.getParameter("customer"));
		int netProfit = Integer.parseInt(request.getParameter("netProfit"));
		int tax = Integer.parseInt(request.getParameter("tax"));
		int totalProfit = Integer.parseInt(request.getParameter("totalProfit"));
		StoreSales s = new StoreSales();
		s.setStoreId(storeId);
		s.setStoreName(storeName);
		s.setSdfDate(date);
		s.setDayOfWeek(yoil.charAt(0));
		s.setCustomer(customer);
		s.setNetProfit(netProfit);
		s.setTax(tax);
		s.setTotalProfit(totalProfit);
		
		int count = new StoreService().countDailySales(storeId,date);
		if(count>0) {
			int count2 = new StoreService().deleteDailySales(storeId,date);
		}
		int result = new StoreService().updateDailySales(s);
		
		String msg = "";
		if(result>0) {
			msg = "등록되었습니다.";
		}else {
			msg = "등록 실패하였습니다.";
		}
		request.setAttribute("loc", "/store/salesView");
		request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
