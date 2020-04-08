package com.empty.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.empty.reservation.service.ReservationService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ReservationCheckServlet
 */
@WebServlet("/reservationCheck")
public class ReservationCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String storeId = request.getParameter("storeId");

		long now = System.currentTimeMillis();

		// 시간의 차이가 -거나 0인것들은 emptySeat로 돌리기 위해
		// store_seat_check에서 seat_yn의 값을 0으로 수정.
		// 1. 받아오기
		List<String> checkYN = new ReservationService().checkYN(storeId, now);
		if (checkYN.size() != 0) {
			
			// 2-1. store_seat_check에 값 바꿔주기.
			int changeYN1 = new ReservationService().changeYN1(storeId, checkYN);
			if (changeYN1 > 0) {
			}

			// 2-2.
			// store_seat seat_check 불러오기
			String seats = new ReservationService().seatList(storeId);
			if (seats != null) {
			}

			String[] list = seats.split(",");
			
			for(int i=0; i<checkYN.size(); i++) {
				int seatNum = Integer.parseInt(checkYN.get(i));
				list[seatNum - 1] = "0";
			}
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
			}
			
		}
		
//		--------------------------------------

		// store_seat_check에서 seat_yn의 값이 1인것만 가져온 것.
		List reSeats = new ReservationService().bringSeat(storeId, now);
		if (reSeats.size() != 0) {
		}

//		JSONObject jo = new JSONObject();
//		jo.put("seats", seats);
		new Gson().toJson(reSeats, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
