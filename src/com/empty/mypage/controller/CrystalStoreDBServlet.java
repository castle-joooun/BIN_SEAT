package com.empty.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.StoreImg2;
import com.empty.search.model.vo.Store;

/**
 * Servlet implementation class CrystalStoreDBServlet
 */
@WebServlet("/enroll/crystalstore.do")
public class CrystalStoreDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrystalStoreDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String storeName = request.getParameter("storeName");
		String storePhone = request.getParameter("storePhone");
		String storeTimestart = request.getParameter("storeTimestart");
		String storeTimeclose = request.getParameter("storeTimeclose");
		String storeInfo = request.getParameter("storeInfo");
		int storePrice = Integer.parseInt(request.getParameter("storePrice"));
		StoreImg2 si= new StoreImg2();
		new MemberService().searchStoreImg(si);
		Store s = new Store();
		s.setStoreId(userId);
		s.setStoreName(storeName);
		s.setStorePhone(storePhone);
		s.setStoreTime(storeTimestart+" ~ "+storeTimeclose);
		s.setStoreInfo(storeInfo);
		s.setStorePrice(storePrice);
		s.setStoreLogo(si.getStoreImg());
		new MemberService().updateStore(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
