package com.empty.event.service;
import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.empty.event.model.dao.EventDao;
import com.empty.event.model.vo.Event;

public class EventService {
	
	private EventDao dao = new EventDao();
	
	public List<Event> searchEvent(int cPage, int numPerPage){
		Connection conn = getConnection();
		List<Event> list = dao.searchEvent(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int eventCount() {
		Connection conn = getConnection();
		int result = dao.eventCount(conn);
		close(conn);
		return result;
	}
	
	public int insertEvent(Event e) {
		Connection conn = getConnection();
		int result = dao.insertEvent(conn, e);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Event selectEvent(int no) {
		Connection conn = getConnection();
		Event e = dao.selectEvent(conn, no);
		close(conn);
		return e;
	}
	
	public Event selectEvent(int no, boolean hasRead) {
		Connection conn = getConnection();
		Event e = dao.selectEvent(conn, no, hasRead);
		if(e != null && !hasRead) {
			int result = dao.updateCount(conn, no);
			if(result > 0) {
				e.setEventCount(dao.selectEvent(conn, no, hasRead).getEventCount());
				commit(conn);
			}
			else rollback(conn);
		}
		close(conn);
		return e;
	}
	
	public int updateEvent(int no, String title, String content) {
		Connection conn = getConnection();
		int result = dao.updateEvent(conn, no, title, content);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteEvent(int no) {
		Connection conn = getConnection();
		int result = dao.deleteEvent(conn, no);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public boolean selectStoreId(String id) {
		Connection conn = getConnection();
		List list = dao.selectStoreId(conn, id);
		boolean flag = false;
		if(list.size() > 0) {
			flag = true;
		}
		close(conn);
		return flag;
	}
	
	public String selectStoreName(String writer) {
		Connection conn = getConnection();
		String name = dao.selectStoreName(conn, writer);
		close(conn);
		return name;
	}
}