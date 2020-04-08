package com.empty.member.model.service;
import static com.empty.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.empty.member.model.dao.MemberDao;
import com.empty.member.model.vo.Member;
import com.empty.member.model.vo.StoreImg;
import com.empty.search.model.vo.Store;

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
	
	public int insertStore(Store s) {
		Connection conn = getConnection();
		int result = dao.insertStore(conn,s);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public int insertStoreImg(String userId,String storeImg1,String storeImg2,String storeImg3,String storeImg4,String storeImg5) {
		Connection conn = getConnection();
		int result = dao.insertStoreImg(conn,userId,storeImg1,storeImg2,storeImg3,storeImg4,storeImg5);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public StoreImg searchStoreImg(StoreImg si) {
		Connection conn = getConnection();
		si = dao.searchStoreImg(conn, si);
		close(conn);
		return si;
	}
	
	public int updateStore(Store s) {
		Connection conn = getConnection();
		int result = dao.updateStore(conn, s);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public int updateBank(String userId,String bankNumber,String bankMaster,String bank) {
		Connection conn = getConnection();
		int result = dao.updateBank(conn, userId, bankNumber, bankMaster, bank);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public List selectUseList(String userId,int cPage, int numPerPage) {
		Connection conn=getConnection();
		List list=dao.selectUseList(conn,userId, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int useListCount() {
	Connection conn=getConnection();
	int count=dao.useListCount(conn);
	close(conn);
	return count;
	}
}
