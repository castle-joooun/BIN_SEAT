package com.empty.reservation.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.empty.reservation.model.vo.Reservation;

public class ReservationDao {

	Properties prop = new Properties();

	public ReservationDao() {
		try {
			String path = ReservationDao.class.getResource("/sql/reservation/reservation.properties").getPath();
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int userPay(Connection conn, String userId, int pay) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("userPay");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, pay);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int userCash(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("userCash");
		int userCash = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userCash = rs.getInt("cash");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return userCash;
	}

	public String seatList(Connection conn, String storeId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("seatList");
		ResultSet rs = null;
		String seats = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seats = rs.getString("SEAT_CHECK");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return seats;
	}

	public int inputSeat(Connection conn, String storeId, String seats) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("useSeat");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seats);
			pstmt.setString(2, storeId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int updatePayUse(Connection conn, String storeName, String userId, int pay, String storeId) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql=prop.getProperty("updatePayUse");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeName);
			pstmt.setString(2, userId);
			pstmt.setInt(3, pay);
			pstmt.setString(4, storeId);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
		

	public int endTime(Connection conn, String storeId, String seat, String time) {
		PreparedStatement pstmt = null;
		int result = 0;
		//String sql = prop.getProperty("endTime");
		String sql="update store_seat_check set seat_end_time=sysdate + interval '"+time+"' hour where store_id=? and seat_num=?";
		//		int time2 = Integer.parseInt(time);
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, time);
			pstmt.setString(1, storeId);
			pstmt.setString(2, seat);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Date userTime(Connection conn, String storeId, String seat) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("userTime");
		Date date = new Date();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			pstmt.setString(2, seat);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				date = rs.getDate("seat_end_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return date;
	}

	public int seatYN(Connection conn, String storeId, String seat) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("seatYN");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			pstmt.setString(2, seat);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List bringSeat(Connection conn, String storeId, long now) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("bringSeats");
		List bringSeat = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				long end = rs.getDate("seat_end_time").getTime();
				long nT = (end - now) / (1000 * 60 * 60);
				long nM = (end - now) % (1000 * 60 * 60) / (1000 * 60);
				String endUserTime = nT + "시간 " + nM + "분";

				Reservation r = new Reservation(rs.getString("store_id"), rs.getString("seat_num"),
						rs.getString("seat_yn"), endUserTime);

				bringSeat.add(r);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return bringSeat;
	}

	public List<String> changeYN(Connection conn, String storeId, long now) {
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		String sql1 = prop.getProperty("bringSeats");
		List<String> list = new ArrayList();
		try {
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, storeId);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				long end = rs1.getDate("seat_end_time").getTime();
				
				long check = end - now;
				if(check <= 0) {
					list.add(rs1.getString("seat_num"));
				}

			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs1);
			close(pstmt1);
		}
		
		return list;

		
	}
	
	public int changeYN1(Connection conn, String storeId, List<String> list) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("changeYN1");
		int result = 0;
		int seatNum = 0;
		try {
			for(int i=0; i<list.size(); i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, storeId);
				pstmt.setString(2, list.get(seatNum));
				pstmt.executeUpdate();
				seatNum++;
				result++;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}
