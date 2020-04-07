package com.empty.member.model.dao;

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
import com.empty.member.model.vo.StoreImg2;
import com.empty.member.model.vo.outMoneyDB;
import com.empty.search.model.vo.Store;

public class MemberDao {

	private Properties prop = new Properties();

	public MemberDao() {
		try {
			String path = MemberDao.class.getResource("/sql/member/query.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getGender());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertOwnerMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertOwnerMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member login(Connection conn, String userId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("login");
		Member m = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("user_Id"));
				m.setUserDiv(rs.getBoolean("user_Div"));
				m.setPassword(rs.getString("password"));
				m.setUserName(rs.getString("user_Name"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setGender(rs.getString("gender"));
				m.setCash(rs.getInt("cash"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				m.setUserAppr(rs.getBoolean("user_appr"));
				m.setBank(rs.getString("bank"));
				m.setBankNumber(rs.getString("bank_number"));
				m.setBankMaster(rs.getString("bank_master"));
				m.setStatus(rs.getString("status"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public int updatePassword(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updatePassword");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getUserId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member findId(Connection conn, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		String sql = prop.getProperty("findId");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("user_id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public Member findPw(Connection conn, String userId, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		String sql = prop.getProperty("findPw");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = new Member();
				m.setUserId(rs.getString("user_id"));
			}
		}catch(SQLException e) { 
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	public boolean selectCheckId(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCheckId");
		boolean flag = true;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return flag;
	}
	
	public boolean selectCheckEmail(Connection conn, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectCheckEmail");
		boolean flag = true;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return flag;
	}
	
	public int insertStore(Connection conn, Store s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertStore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getStoreId());
			pstmt.setString(2, s.getStoreName());
			pstmt.setString(3, s.getStorePhone());
			pstmt.setString(4, s.getStoreTime());
			pstmt.setString(5, s.getStoreInfo());
			pstmt.setString(6, s.getStoreFacility());
			pstmt.setString(7, s.getStoreAddress());
			pstmt.setString(8, s.getStoreLogo());
			pstmt.setInt(9, s.getStorePrice());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertStoreImg(Connection conn,String storeImg1,String storeImg2,String storeImg3,String storeImg4,String storeImg5) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertStoreImg2");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeImg1+","+storeImg2+","+storeImg3+","+storeImg4+","+storeImg5);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public StoreImg2 searchStoreImg(Connection conn, StoreImg2 si) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("searchStoreImg");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				si.setStoreImg(rs.getString("store_img"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return si;
	}
	
	public int updateStore(Connection conn, Store s) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateStore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getStoreName());
			pstmt.setString(2, s.getStorePhone());
			pstmt.setString(3, s.getStoreTime());
			pstmt.setString(4, s.getStoreInfo());
			pstmt.setInt(5, s.getStorePrice());
			pstmt.setString(6, s.getStoreId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBank(Connection conn,String userId,String bankNumber,String bankMaster,String bank) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBank");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bank);
			pstmt.setString(2, bankNumber);
			pstmt.setString(3, bankMaster);
			pstmt.setString(4, userId);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public List selectUseList(Connection conn,int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list=new ArrayList();
		String sql=prop.getProperty("selectUseList");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				outMoneyDB omdb = new outMoneyDB();
				omdb.setUserId(rs.getString("user_id"));
				omdb.setOutputNum(rs.getInt("output_num"));
				omdb.setOmDate(rs.getDate("omdate"));
				omdb.setOmNumber(rs.getString("bank_number"));
				omdb.setOm(rs.getInt("om"));
				omdb.setAfterOm(rs.getInt("after_om"));
				list.add(omdb);				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int useListCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		String sql=prop.getProperty("useListCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return count;
	}
}
