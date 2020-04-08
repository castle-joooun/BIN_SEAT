package com.empty.store.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.empty.admin.model.dao.AdminDao;
import com.empty.store.model.vo.StoreSales;

public class StoreDao {
Properties prop = new Properties();
	
	public StoreDao() {
		try {
			String path = AdminDao.class.getResource("/sql/store/storeSales.properties").getPath();
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List selectStoreSales(Connection conn, String id, String date) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectStoreSales");
		
		List<StoreSales> list = new ArrayList();
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, date);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StoreSales s = new StoreSales();
				s.setStoreId(rs.getString("STORE_ID"));
				s.setStoreName(rs.getString("STORE_NAME"));
				s.setEnDate(rs.getDate("EN_DATE"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				s.setSdfDate(sdf.format(rs.getDate("EN_DATE")));
				s.setDayOfWeek(rs.getString("DAY_OF_WEEK").charAt(0));
				s.setCustomer(rs.getInt("CUSTOMER"));
				s.setNetProfit(rs.getInt("NET_PROFIT"));
				s.setTax(rs.getInt("TAX"));
				s.setTotalProfit(rs.getInt("TOTAL_PROFIT"));
				list.add(s);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return list;
		
	}

	public int selectPayUse(Connection conn, String id, String date) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("sumPayUse");
		
		int result = 0;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, date);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
		
		
	}

	public int countPayUse(Connection conn, String id, String date) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("countPayUse");
		int result = 0;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, date);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}
	public String selectName(Connection conn,String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectName");
		String name =null;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return name;
	}

	public int countDailySales(Connection conn, String id, String date) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("countDailySales");
		int count = 0;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, date);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public int deleteDailySales(Connection conn, String id, String date) {
		PreparedStatement pstmt = null;
		int result =0;
		String sql = prop.getProperty("deleteSales");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, date);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateDailySales(Connection conn, StoreSales s) {
		Statement stmt = null;
		int result = 0;
		String sql = "INSERT INTO DAILY_SALES (STORE_ID,STORE_NAME,EN_DATE,DAY_OF_WEEK, CUSTOMER, NET_PROFIT, TAX, TOTAL_PROFIT)"
				+ " VALUES('"+s.getStoreId()+"','"+s.getStoreName()+"',TO_DATE('"+s.getSdfDate()+"','RR/MM/DD'),'"+Character.toString(s.getDayOfWeek())+"',"
				+s.getCustomer()+","+s.getNetProfit()+","+s.getTax()+","+s.getTotalProfit()+")";
		
		
		try {
			stmt = conn.createStatement();
			result= stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		
		return result;
		
	}
	
	
	
	
	
}
