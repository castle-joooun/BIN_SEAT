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
		System.out.println(s.toString());
		int result = dao.insertStore(conn,s);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result; 
	}
	
	public int insertStoreImg(String userId,List list) {
		int result=0;
		int num=0;
		System.out.println(list.size());
		System.out.println(userId);
		System.out.println(list.get(0));
		for(int i = 0; i<list.size();i++) {
			System.out.println(num);
		Connection conn = getConnection();
		result = dao.insertStoreImg(conn,userId,list,num,result);
		if(result>0) {
			commit(conn);
			num++;
			result++;
		}
		else {
			rollback(conn);
		}
		close(conn);
		}
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
	
	public int useListCount(String userId) {
	Connection conn=getConnection();
	int count=dao.useListCount(conn, userId);
	close(conn);
	return count;
	}
	
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int count=dao.updateMember(conn, m);
		close(conn);
		return count;
	}
	
	public List selectUseList2(String userId,int cPage, int numPerPage) {
		Connection conn=getConnection();
		List list=dao.selectUseList2(conn,userId, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int useListCount2(String userId) {
		Connection conn=getConnection();
		int count=dao.useListCount2(conn, userId);
		close(conn);
		return count;
	}
}
