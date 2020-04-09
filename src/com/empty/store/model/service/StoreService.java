package com.empty.store.model.service;

import java.util.List;
import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.empty.search.model.vo.Store;
import com.empty.store.model.dao.StoreDao;
import com.empty.store.model.vo.StoreSales;

public class StoreService {
	
	StoreDao dao = new StoreDao();
	

	public List selectStoreSales(String id, String date) {
		Connection conn = getConnection();
		List list = dao.selectStoreSales(conn,id,date);
		close(conn);
		return list;
	}


	public int selectPayUse(String id, String date) {
		Connection conn = getConnection();
		int sumResult = dao.selectPayUse(conn,id,date);
		close(conn);
		return sumResult;
		
	}


	public int countPayUse(String id, String date) {
		Connection conn = getConnection();
		int countCus = dao.countPayUse(conn,id,date);
		close(conn);
		return countCus;
	}


	public String selectName(String id) {
		Connection conn = getConnection();
		String name = dao.selectName(conn,id);
		close(conn);
		return name;
	}
	public int countDailySales(String storeId, String date) {
		Connection conn = getConnection();
		int count = dao.countDailySales(conn,storeId,date);
		close(conn);
		return count;
	}

	public int updateDailySales(StoreSales s) {
		Connection conn = getConnection();
		int result = dao.updateDailySales(conn,s);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public int deleteDailySales(String storeId, String date) {
		Connection conn = getConnection();
		int result = dao.deleteDailySales(conn,storeId,date);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	public Store selectStore(String id) {
		Connection conn = getConnection();
		Store s = dao.selectStore(conn, id);
		close(conn);
		return s;
	}


	

	
	
	
}
