package com.empty.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.store.model.service.StoreService;
import com.empty.store.model.vo.StoreSales;
import com.google.gson.Gson;

/**
 * Servlet implementation class StoreDailySalesAjax
 */
@WebServlet("/store/dailySales/ajax")
public class DailySalesAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailySalesAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date = request.getParameter("date");
		String id = request.getParameter("storeId");
		
		int sumResult = new StoreService().selectPayUse(id,date);
		int countCus = new StoreService().countPayUse(id,date);
		String storeName = new StoreService().selectName(id);
		
		StoreSales s = null;
		if(sumResult>0) {
			s=new StoreSales();
			s.setCustomer(countCus);
			int net = sumResult*100/110;
			int tax = sumResult-net;
			
			s.setStoreId(id);
			s.setNetProfit(net);
			s.setTax(tax);
			s.setTotalProfit(sumResult);
			s.setStoreName(storeName);
		}
				
		
		response.setContentType("application/json;charset=UTF-8");
		new Gson().toJson(s,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
