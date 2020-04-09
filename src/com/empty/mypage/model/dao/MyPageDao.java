package com.empty.mypage.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.empty.member.model.vo.Member;
import com.empty.mypage.model.vo.PayUse;
import com.empty.mypage.model.dao.MyPageDao;
import com.empty.mypage.model.vo.InputMoneyList;

import oracle.jdbc.proxy.annotation.Pre;


public class MyPageDao {
	private Properties prop=new Properties();
	
	public MyPageDao() {
		try {
			String path=MyPageDao.class.getResource("/sql/mypage/mypage-query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int updateMember(Connection conn,Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getUserName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int deleteMember(Connection conn,String userId,String password) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	


	
	public List searchPayUse(Connection conn,String userId,int cPage,int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchPayUse");
		List<PayUse> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PayUse p=new PayUse();
				p.setUser_id(rs.getString("user_Id"));
				p.setStime(rs.getDate("stime"));
				p.setPaymoney(rs.getInt("paymoney"));
				p.setStore_name(rs.getString("store_name"));
				list.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
		
	}
	
	
	public int payUseCount(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("payUseCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	public List searchPayCharge(Connection conn,String userId2,int cPage2,int numPerPage2) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchPayCharge");
		List<InputMoneyList> list2=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId2);
			pstmt.setInt(2, (cPage2-1)*numPerPage2+1);
			pstmt.setInt(3, cPage2*numPerPage2);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				InputMoneyList iml=new InputMoneyList();
				iml.setUser_id(rs.getString("user_id"));
				iml.setInput_num(rs.getInt("input_num"));
				iml.setIpdate(rs.getDate("ipdate"));
				iml.setIpmoney(rs.getInt("ipmoney"));
				iml.setAfter_im(rs.getInt("after_im"));
				list2.add(iml);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list2;
		
	}
	
	
	public int payChargeCount(Connection conn,String userId2) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=prop.getProperty("payChargeCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId2);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	public int insertStoreSeat(Connection conn, String storeId, int col, int row, String seatNum, String seatCheck) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertStoreSeat");
		System.out.println(storeId);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			pstmt.setInt(2, col);
			pstmt.setInt(3, row);
			pstmt.setString(4, seatNum);
			pstmt.setString(5, seatCheck);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertStoreSeatCheck(Connection conn, String storeId, String num) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertStoreSeatCheck");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeId);
			pstmt.setString(2, num);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
}
