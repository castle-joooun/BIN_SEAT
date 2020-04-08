package com.empty.mypage.service;

import java.sql.Connection;
import java.util.List;

import com.empty.mypage.model.dao.MyPageDao;
import com.empty.mypage.model.vo.InputMoneyList;
import com.empty.mypage.model.vo.PayUse;

import com.empty.member.model.vo.Member;
import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.rollback;
import static com.empty.common.JDBCTemplate.getConnection;

public class MyPageService {

	private MyPageDao dao=new MyPageDao();
	
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=dao.updateMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteMember(String userId,String password) {
		Connection conn=getConnection();
		int result=dao.deleteMember(conn,userId,password);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	


	
	
	public List<PayUse> searchPayUse(String userId,int cPage,int numPerPage) {
		Connection conn = getConnection();
		List<PayUse> list = dao.searchPayUse(conn,userId,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	public int payUseCount(String userId) {
		Connection conn=getConnection();
		int result=dao.payUseCount(conn,userId);
		close(conn);
		return result;
	}
	
	public List<InputMoneyList> searchPayCharge(String userId2,int cPage2,int numPerPage2) {
		Connection conn = getConnection();
		List<InputMoneyList> list2 = dao.searchPayCharge(conn,userId2,cPage2,numPerPage2);
		close(conn);
		return list2;
		
	}
	public int payChargeCount(String userId) {
		Connection conn=getConnection();
		int result=dao.payChargeCount(conn,userId);
		close(conn);
		return result;
	}
	
	public int insertStoreSeat(String storeId, int col, int row, String seatNum, String seatCheck) {
		Connection conn = getConnection();
		int result = dao.insertStoreSeat(conn, storeId, col, row, seatNum, seatCheck);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertStoreSeatCheck(String storeId, List<String> nums) {
		int result = 0;
		for(int i=0; i<nums.size(); i++) {
			Connection conn = getConnection();
			
			int result1 = dao.insertStoreSeatCheck(conn, storeId, nums.get(i));
			if(result1 > 0) {
				commit(conn);
				result++;
			} else {
				rollback(conn);
			}
			
			close(conn);
		}
		
		return result;
	}
	
	
}
