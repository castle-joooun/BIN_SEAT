package com.empty.reservation.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.empty.reservation.model.dao.ReservationDao;

public class ReservationService {

	private ReservationDao dao = new ReservationDao();
	
	public int userPay(String userId, int pay) {
		Connection conn = getConnection();
		// 유저 cash 마이너스 해주기.
		int result = dao.userPay(conn, userId, pay);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	// 유저 돈 가져오기
	public int userCash(String userId) {
		Connection conn = getConnection();
		int userCash = dao.userCash(conn, userId);
		close(conn);
		
		return userCash;
	}
	
	public String seatList(String storeId) {
		Connection conn = getConnection();
		String seats = dao.seatList(conn, storeId);
		close(conn);
		return seats;
	}
	
	public int inputSeat(String storeId, String seats) {
		Connection conn = getConnection();
		int result = dao.inputSeat(conn, storeId, seats);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int updatePayUse(String storeName, String userId, int pay, String storeId) {
		Connection conn = getConnection();
		int result = dao.updatePayUse(conn,storeName, userId, pay,storeId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result ;
	}
	
	
	public int endTime(String storeId, String seat, String time) {
		Connection conn = getConnection();
		int result = dao.endTime(conn, storeId, seat, time);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public Date userTime(String storeId, String seat) {
		Connection conn = getConnection();
		Date date = dao.userTime(conn, storeId, seat);
		close(conn);
		
		return date;
	}
	
	public int seatYN(String storeId, String seat) {
		Connection conn = getConnection();
		int result = dao.seatYN(conn, storeId, seat);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public List bringSeat(String storeId, long now) {
		Connection conn = getConnection();
		List bringSeat = dao.bringSeat(conn, storeId, now);
		close(conn);
		return bringSeat;
	}
	
	public List<String> checkYN(String storeId, long now) {
		Connection conn = getConnection();
		List<String> result = dao.changeYN(conn, storeId, now);
		close(conn);
		return result;
	}
	
	public int changeYN1(String storeId, List<String> list) {
		Connection conn = getConnection();
		int result = dao.changeYN1(conn, storeId, list);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	
}
