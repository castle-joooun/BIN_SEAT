package com.empty.cash.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.empty.member.model.vo.Member;
import com.empty.member.model.vo.outMoneyDB;
import com.empty.search.model.dao.SearchDao;

public class VinDao {
	
	Properties prop = new Properties();
	
	public VinDao() {
		try {
			String path = SearchDao.class.getResource("/sql/cash/cash.properties").getPath();
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertCash(Connection conn, int amount, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql = prop.getProperty("insertCash");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectUser(Connection conn, Member m, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getString("USER_ID"));
				m.setUserName(rs.getString("USER_NAME"));
				m.setEmail(rs.getString("EMAIL"));
				m.setPhone(rs.getString("PHONE"));
				m.setAddress(rs.getString("ADDRESS"));
				m.setGender(rs.getString("GENDER"));
				m.setCash(rs.getInt("CASH"));
				m.setEnrollDate(rs.getDate("ENROLLDATE"));
				m.setBank(rs.getString("bank"));
				m.setBankNumber(rs.getString("bank_number"));
				m.setBankMaster(rs.getString("bank_master"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int payCharge(Connection conn, Member m, int amount, String pay) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql = prop.getProperty("payCharge");
		String payday="";
		switch(pay) {
			case "phone" :payday="핸드폰결제";break;
			case "trans" :payday="계좌이체";break;
			case "card" :payday="카드결제";break;
			case "vbank" :payday="가상계좌";break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setInt(2, amount);
			pstmt.setString(3, payday);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int minusCash(Connection conn, Member m, int money) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql = prop.getProperty("minusCash");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int outMoneyListDB(Connection conn, Member m, int money) {
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("selectOutMoneyList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, "12612318801010기업은행");
			pstmt.setInt(4, money);
			pstmt.setInt(5, m.getCash()-money);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
