package com.empty.admin.model.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.empty.admin.model.dao.AdminDao;
import com.empty.member.model.vo.Member;

public class AdminService {

	private AdminDao dao = new AdminDao();
	
	
	public List<Member> selectMember(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = dao.selectMember(conn,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	public int memberCount() {
		Connection conn = getConnection();
		int result = dao.memberCount(conn);
		close(conn);
		return result;
	}
	public List selectMember(String type,String keyword,int cPage,int numPerPage) {
		
		Connection conn = getConnection();
		List<Member> list = dao.selectMember(conn,type,keyword,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	public int memberCount(String type,String keyword) {
		Connection conn = getConnection();
		int result = dao.memberCount(conn,type,keyword);
		close(conn);
		return result;
	}
	
	
}