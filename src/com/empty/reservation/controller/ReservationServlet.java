package com.empty.reservation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.reservation.service.ReservationService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/reservation.do")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userId = request.getParameter("userId");
	      String storeId = request.getParameter("storeId");
	      String seat = request.getParameter("seat");
	      int pay = Integer.parseInt(request.getParameter("pay"));
	      String time = request.getParameter("time");
	      String storeName = request.getParameter("storeName");
	      
	      // 1. 유저 캐시 빼준다.
	      // member cash 빼주는 기능
	      int userPay = new ReservationService().userPay(userId, pay);
	      if (userPay > 0) {
	      }
	      // 2. 유저 캐시 리로드 해줄꺼 반할 로직 짜준다.
	      int userCash = new ReservationService().userCash(userId);
	      if (userCash > 0) {
	      }

	      // 3. 스토어 해당 자리를 변경해준다.
//	      int changeSeatUse = new ReservationService().changeSeatUse(userId, storeId, seat);
//	      if(changeSeatUse > 0) {
//	         System.out.println("자리 바꼈어!");
//	      }

	      // store_seat seat_check 불러오기
	      String seats = new ReservationService().seatList(storeId);
	      if (seats!=null) {
	      }

	      int seatNum = Integer.parseInt(seat);
	      String[] list = seats.split(",");
	      list[seatNum-1] = "1";
	      String tranSeats = "";

	      for (int i = 0; i < list.length; i++) {
	         if (i == list.length - 1) {
	            tranSeats += list[i];
	         } else {
	            tranSeats += list[i] + ",";
	         }
	      }

	      // store_seat seat_check 반환하기 (1=사용중)
	      int useSeat = new ReservationService().inputSeat(storeId, tranSeats);
	      if (useSeat > 0) {
	         int payUse = new ReservationService().updatePayUse(storeName,userId,pay,storeId);
				
				if(payUse>0) {
				}
	      }

	      // 변환된 시간 해당 자리에 넣기
	      int timeOk = new ReservationService().endTime(storeId, seat, time);
	      if (timeOk > 0) {
	      }

	      // 변환된 시간 가져오기
	      Date userTime = new ReservationService().userTime(storeId, seat);
	      SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
	      String endUserTime = "";
	      if (userTime!=null) {
	         long dayTime1 = System.currentTimeMillis();
	         long dayTime2 = userTime.getTime();
	         long nT = (dayTime2 - dayTime1) / (1000 * 60 * 60);
	         long nM = (dayTime2 - dayTime1) % (1000 * 60 * 60) / (1000 * 60);
	         endUserTime = nT + "시간 " + nM + "분";
	         
	      }
	      
	      // store_seat_check의 seat_yn에 사용 표시 넣기
	      int seatYN = new ReservationService().seatYN(storeId, seat);
	      if(seatYN >0 ) {
	      }

	      JSONObject jo = new JSONObject();
	      jo.put("userCash", userCash);
	      jo.put("endTime", endUserTime);
	      new Gson().toJson(jo, response.getWriter());


		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
