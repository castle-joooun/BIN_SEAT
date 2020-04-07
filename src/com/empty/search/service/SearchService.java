package com.empty.search.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.rollback;

import static com.empty.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.empty.member.model.vo.outMoneyDB;
import com.empty.search.model.dao.SearchDao;
import com.empty.search.model.vo.Store;
import com.empty.search.model.vo.StoreSeat;

public class SearchService {

	private SearchDao dao = new SearchDao();
	
	public List<Store> totalSearch(String searchBox) {
		Connection conn = getConnection();
		List<Store> list = dao.totalSearch(conn, searchBox);
		close(conn);
		return list;
	}
	public List<Store> totalSearch(String keyword,int cPage,int numPerPage){
		Connection conn = getConnection();
		List<Store> list = dao.totalSearch(conn, keyword,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	
	public int dataCount(String keyword) {
		Connection conn = getConnection();
		int result = dao.dataCount(conn,keyword);
		close(conn);
		return result;
	}
	public Store store(String id) {
		Connection conn = getConnection();
		Store s = dao.store(conn, id);
		close(conn);
		return s;
	}
	
	public List<String> storeImgs(String id) {
		Connection conn = getConnection();
		List<String> imgs = dao.storeImgs(conn, id);
		close(conn);
		return imgs;
	}
	
	public StoreSeat storeSeat(String id) {
		Connection conn = getConnection();
		StoreSeat ss = dao.storeSeat(conn, id);
		close(conn);
		return ss;
	}
	
	public int storeFavoriteInsert(String userId, String storeLogo, String storeId, String storeName) {
		Connection conn = getConnection();
		int result = dao.storeFavoriteInsert(conn, userId, storeLogo, storeId, storeName);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	public int storeFavoriteDelete(String userId, String storeId) {
		Connection conn = getConnection();
		int result = dao.storeFavoriteDelete(conn, userId, storeId);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	public String storeFavoriteCheck(String userId, String storeId) {
		Connection conn = getConnection();
		int result = dao.storeFavoriteCheck(conn, userId, storeId);
		int favoriteSize = dao.favoriteSize(conn, userId);
		String url = "";
		System.out.println(favoriteSize);
		if(favoriteSize!=6 && result>0) {
			url = "image/favorite-use.png";
		} else {
			url = "image/favorite-empty.png";
		}
		
		close(conn);
		return url;
	}
	
	public List favoriteList(String userId) {
		Connection conn = getConnection();
		List list = dao.favoriteList(conn, userId);
		close(conn);
		return list;
	}

	public int favoriteSize(String userId) {
		Connection conn = getConnection();
		int favoriteSize = dao.favoriteSize(conn, userId);
		close(conn);
		return favoriteSize;
	}

	public List outMoneyList(String userId, outMoneyDB omdb) {
		Connection conn = getConnection();
		List list = dao.outMoneyList(conn, userId, omdb);
		close(conn);
		return list;
	}
	
	public int omlCount() {
		Connection conn=getConnection();
		int count=dao.omlCount(conn);
		close(conn);
		return count;
	}
	
	public Store crystalstore(String userId) {
	      Connection conn = getConnection();
	      Store s = dao.crystalstore(conn, userId);
	      close(conn);
	      return s;
	   }
	
	
	
	
}
