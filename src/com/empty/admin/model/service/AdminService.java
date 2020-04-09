package com.empty.admin.model.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.empty.admin.model.dao.AdminDao;
import com.empty.member.model.vo.Member;
import com.empty.search.model.vo.Store;

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
	public List selectRequestStore(int cPage,int numPerPage) {
		Connection conn = getConnection();
		List <Member> list = dao.selectRequestStore(conn,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	public int requestStoreCount() {
		Connection conn = getConnection();
		int result = dao.requestStoreCount(conn);
		close(conn);
		return result;
	}
	public int updateAppr(String userId) {
		Connection conn = getConnection();
		int result = dao.updateAppr(conn,userId);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
//	public int updateAppr(String[] userid) {
//		Connection conn = getConnection();
//		int result=0;
//		for(String s: userid) {
//			int i=0;
//			i = dao.updateAppr(conn,s);
//			if(i>0) {
//				commit(conn);
//				result+=i;
//			}
//			else {
//				rollback(conn);
//			}
//		}
//		
//		return result;
//	}
	public List<Store> selectStore(int cPage,int numPerPage) {
		Connection conn = getConnection();
		List<Store> list = dao.selectStore(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	public int selectStore() {
		Connection conn = getConnection();
		int result = dao.selectStore(conn);
		close(conn);
		return result;
		
	}
	public int deleteStore(String id) {
		Connection conn = getConnection();
		int result = dao.deleteStoreImg(conn,id);
		if (result>0) {
			commit(conn);
		}
		else {
			rollback(conn);	
		}
		result =dao.deleteStoreSeat(conn,id);
		if (result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		//result =dao.deleteStoreComment(conn,id);
//		if (result>0) {
//			commit(conn);
//			
//		}
//		else {
//			rollback(conn);
//			close(conn);
//			return result;
//		}
		result = dao.deleteStoreSeatCheck(conn,id);
		if (result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		result=dao.deleteStore(conn,id);
		if (result>0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	
	
}
