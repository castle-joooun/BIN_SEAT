package com.empty.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.StoreImg;
import com.empty.search.model.vo.Store;
import com.google.gson.Gson;


/**
 * Servlet implementation class EnrollStoreDBgoServlet
 */
@WebServlet("/enroll/store")
public class EnrollStoreDBgoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollStoreDBgoServlet() {
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
		String[] storeFacirity = request.getParameterValues("storeFacirity");
		String storeAddress = request.getParameter("storeAddress");
		String fileupload = request.getParameter("fileupload");
		int storePrice = Integer.parseInt(request.getParameter("storePrice"));
		String fileuploadName = "image/"+fileupload.substring(fileupload.lastIndexOf("\\")+1);
		String storeFaciritys="";
		String msg="";
		for(int i=0;i<storeFacirity.length;i++) {
			storeFaciritys +=storeFacirity[i]+",";
		}
		String storeFacirityss = storeFaciritys.substring(0,storeFaciritys.lastIndexOf(","));
		System.out.println(storeFacirityss);
		Store s = new Store();
		s.setStoreId(userId);
		s.setStoreName(storeName);
		s.setStorePhone(storePhone);
		s.setStoreTime(storeTimestart+" ~ "+storeTimeclose);
		s.setStoreInfo(storeInfo);
		s.setStoreFacility(storeFacirityss);
		s.setStoreAddress(storeAddress);
		s.setStoreLogo(fileuploadName);
		s.setStorePrice(storePrice);
		int result = new MemberService().insertStore(s);
		if(result > 0) {
			msg="매장이 등록되었습니다.";
		}else {
			msg="매장등록에 실패하였습니다.";
		}
		
		JSONObject jsonObj=new JSONObject();
		jsonObj=new JSONObject();
		jsonObj.put("msg", msg);
		
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
