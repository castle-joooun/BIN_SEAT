package com.empty.member.model.service;
import static com.empty.common.JDBCTemplate.*;

import java.sql.Connection;

import com.empty.member.model.dao.MemberDao;
import com.empty.member.model.vo.Member;

public class MemberService {
	
	private MemberDao dao = new MemberDao();
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = dao.insertMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		System.out.println(result);
		return result;
	}
	
	public int insertOwnerMember(Member m) {
		Connection conn = getConnection();
		int result = dao.insertOwnerMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member login(String userId, String password) {
		Connection conn = getConnection();
		Member m = dao.login(conn, userId, password);
		close(conn);
		return m;
	}
	
	public int updatePassword(Member m) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public Member findId(String email) {
		Connection conn = getConnection();
		Member m = dao.findId(conn, email);
		close(conn);
		return m;
	}
	
	public Member findPw(String userId, String email) {
		Connection conn = getConnection();
		Member m = dao.findPw(conn, userId, email);
		close(conn);
		return m;
	}
	
	public boolean selectCheckId(String userId) {
		Connection conn = getConnection();
		boolean flag = dao.selectCheckId(conn, userId);
		close(conn);
		return flag;
	}
	
	public boolean selectCheckEmail(String email) {
		Connection conn = getConnection();
		boolean flag = dao.selectCheckEmail(conn, email);
		close(conn);
		return flag;
	}

}
