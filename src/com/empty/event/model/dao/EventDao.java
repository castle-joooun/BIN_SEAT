package com.empty.event.model.dao;

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

import com.empty.event.model.vo.Event;
import com.empty.search.model.vo.Store;

public class EventDao {

	private Properties prop = new Properties();

	public EventDao() {
		try {
			String path = EventDao.class.getResource("/sql/event/event.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public List<Event> searchEvent(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("searchEvent");
		List<Event> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Event e = new Event();
				e.setEventNo(rs.getInt("EVENT_NO"));
				e.setEventTitle(rs.getString("EVENT_TITLE")); 
				e.setEventWriter(rs.getString("EVENT_WRITER"));
				e.setEventContent(rs.getString("EVENT_CONTENT"));
				e.setEventDate(rs.getDate("EVENT_DATE"));
				e.setEventCount(rs.getInt("EVENT_COUNT"));
				list.add(e);
			}
		}catch(SQLException q) {
			q.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int eventCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("eventCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int insertEvent(Connection conn, Event e) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertEvent");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getEventTitle());
			pstmt.setString(2, e.getEventWriter());
			pstmt.setString(3, e.getEventContent());
			result = pstmt.executeUpdate();
		}catch(SQLException q) {
			q.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public Event selectEvent(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectEvent");
		Event e = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				e = new Event();
				e.setEventNo(rs.getInt("EVENT_NO"));
				e.setEventTitle(rs.getString("EVENT_TITLE")); 
				e.setEventWriter(rs.getString("EVENT_WRITER"));
				e.setEventContent(rs.getString("EVENT_CONTENT"));
				e.setEventDate(rs.getDate("EVENT_DATE"));
				e.setEventCount(rs.getInt("EVENT_COUNT"));
			}
		}catch(SQLException q) {
			q.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return e;
	}

	public Event selectEvent(Connection conn, int no, boolean hasRead) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectEvent");
		Event e = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				e = new Event();
				e.setEventNo(rs.getInt("EVENT_NO"));
				e.setEventTitle(rs.getString("EVENT_TITLE")); 
				e.setEventWriter(rs.getString("EVENT_WRITER"));
				e.setEventContent(rs.getString("EVENT_CONTENT"));
				e.setEventDate(rs.getDate("EVENT_DATE"));
				e.setEventCount(rs.getInt("EVENT_COUNT"));
			}
		}catch(SQLException q) {
			q.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return e;
	}

	public int updateCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateEvent(Connection conn, int no, String title, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateEvent");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteEvent(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteEvent");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Store> selectStoreId(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		Store s = null;
		String sql = prop.getProperty("selectStoreId");
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				s = new Store(
						rs.getString("store_id"),
						rs.getString("store_name"),
						rs.getString("store_phone"),
						rs.getString("store_time"),
						rs.getString("store_info"),
						rs.getString("store_facility"),
						rs.getString("store_address"),
						rs.getString("store_logo"),
						rs.getInt("store_price")
						);
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
	
	public String selectStoreName(Connection conn,String writer) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectStoreName");
		String name = null;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, writer);
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


}