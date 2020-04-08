package com.empty.admin.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.empty.member.model.vo.Member;
import com.empty.search.model.vo.Store;

public class AdminDao {
	Properties prop = new Properties();
	
	public AdminDao() {
		try {
			String path = AdminDao.class.getResource("/sql/admin/admin.properties").getPath();
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List selectMember(Connection conn,int cPage,int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMemberPage");
		List<Member> list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setUserId(rs.getString("USER_ID"));
				m.setUserDiv(rs.getBoolean("USER_DIV"));
				m.setUserName(rs.getString("USER_NAME"));
				m.setEmail(rs.getString("EMAIL"));
				m.setPhone(rs.getString("PHONE"));
				m.setAddress(rs.getString("ADDRESS"));
				m.setGender(rs.getString("GENDER"));
				m.setCash(rs.getInt("CASH"));
				m.setEnrollDate(rs.getDate("ENROLLDATE"));
				//m.setUserAppr(rs.getBoolean("USER_APPR"));
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int memberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("memberCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
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
	public List selectMember(Connection conn,String type,String keyword,int cPage,int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List <Member> list = new ArrayList();
		String sql = prop.getProperty("selectMember"+type);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setUserId(rs.getString("USER_ID"));
				m.setUserDiv(rs.getBoolean("USER_DIV"));
				m.setUserName(rs.getString("USER_NAME"));
				m.setEmail(rs.getString("EMAIL"));
				m.setPhone(rs.getString("PHONE"));
				m.setAddress(rs.getString("ADDRESS"));
				m.setGender(rs.getString("GENDER"));
				m.setCash(rs.getInt("CASH"));
				m.setEnrollDate(rs.getDate("ENROLLDATE"));
				//m.setUserAppr(rs.getBoolean("USER_APPR"));
				list.add(m);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int memberCount(Connection conn,String type,String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("memberCount"+type);
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
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
	
	public List<Member> selectRequestStore(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList();
		String sql = prop.getProperty("selectReqeustStore");
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setUserId(rs.getString("USER_ID"));
				m.setUserName(rs.getString("user_name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
				
		return list;
	}

	public int requestStoreCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("requestStoreCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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

	public int updateAppr(Connection conn, String userId) {
		PreparedStatement pstmt= null;
		int result = 0;
		String sql = prop.getProperty("updateAppr");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

//	public int updateAppr(Connection conn, String[] userid) {
//		PreparedStatement pstmt= null;
//		int result = 0;
//		String sql = prop.getProperty("updateAppr");
//		for(String s : userid) {
//			try {
//				
//				pstmt=conn.prepareStatement(sql);
//				pstmt.setString(1, s);
//				result+=pstmt.executeUpdate();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}finally {
//				close(pstmt);
//			}
//		}
//		
//		System.out.println("updateAppr: "+result);
//		return result;
//	}

	public List<Store> selectStore(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		List<Store> list = new ArrayList();
		String sql = prop.getProperty("selectStore");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Store s = new Store();
				s.setStoreId(rs.getString("STORE_ID"));
				s.setStoreName(rs.getString("STORE_NAME"));
				s.setStorePhone(rs.getString("STORE_PHONE"));
				s.setEmail(rs.getString("EMAIL"));
				s.setStoreAddress(rs.getString("STORE_ADDRESS"));
				SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
				String date = sdf.format(rs.getDate("ENROLLDATE"));
				s.setEnrollDate(date);
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

	public int selectStore(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("countStore");
		try {
			pstmt=conn.prepareStatement(sql);
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

	public int deleteStoreImg(Connection conn, String id) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteStoreImg");
		int result = 0;

		try {
			//나중에 user_comment테이블에서도 삭제 해야함
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteStoreSeat(Connection conn, String id) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteStoreSeat");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	//유저코멘트 완성되면 이것도 추가해야함
//	public int deleteStoreComment(Connection conn, String id) {
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("deleteStoreComment");
//		int result = 0;
//
//		try {
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			result = pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}
//		return result;
//	}

	public int deleteStore(Connection conn, String id) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteStore");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}



}
