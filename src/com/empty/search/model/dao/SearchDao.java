package com.empty.search.model.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.empty.member.model.vo.outMoneyDB;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.empty.search.model.vo.Store;
import com.empty.search.model.vo.StoreSeat;


public class SearchDao {
	
	Properties prop = new Properties();
	
	public SearchDao() {
		try {
			String path = SearchDao.class.getResource("/sql/search/search.properties").getPath();
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Store> totalSearch(Connection conn, String searchBox) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM STORE WHERE(STORE_NAME LIKE '%" + searchBox + "%' OR STORE_INFO LIKE '%" + searchBox + 
				"%' OR STORE_FACILITY LIKE '%" + searchBox + "%' OR STORE_ADDRESS LIKE '%" + searchBox + "%')";
		List<Store> list = new ArrayList();
		Store s = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				s = new Store();
						s.setStoreId(rs.getString("store_id"));
						s.setStoreName(rs.getString("store_name"));
						s.setStorePhone(rs.getString("store_phone"));
						s.setStoreTime(rs.getString("store_time"));
						s.setStoreInfo(rs.getString("store_info"));
						s.setStoreFacility(rs.getString("store_facility"));
						s.setStoreAddress(rs.getString("store_address"));
						s.setStoreLogo(rs.getString("store_logo"));
						s.setStorePrice(rs.getInt("store_price"));
				
				list.add(s);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return list;
	}
	
	public List<Store> totalSearch(Connection conn, String keyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("totalSearch");
		List <Store> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setString(3,"%"+keyword+"%");
			pstmt.setString(4,"%"+keyword+"%");
			pstmt.setInt(5,(cPage-1)*numPerPage+1);
			pstmt.setInt(6,cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Store s = new Store();
				s.setStoreId(rs.getString("store_id"));
				s.setStoreName(rs.getString("store_name"));
				s.setStorePhone(rs.getString("store_phone"));
				s.setStoreTime(rs.getString("store_time"));
				s.setStoreInfo(rs.getString("store_info"));
				s.setStoreFacility(rs.getString("store_facility"));
				s.setStoreAddress(rs.getString("store_address"));
				s.setStoreLogo(rs.getString("store_logo"));
				s.setStorePrice(rs.getInt("store_price"));
		
				
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int dataCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("dataCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setString(2,"%"+keyword+"%");
			pstmt.setString(3,"%"+keyword+"%");
			pstmt.setString(4,"%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public Store store(Connection conn, String id) {
		PreparedStatement pstmt = null;
		Store s = null;
		ResultSet rs = null;
		String sql = prop.getProperty("store");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				s = new Store();
				s.setStoreId(rs.getString("store_id"));
				s.setStoreName(rs.getString("store_name"));
				s.setStorePhone(rs.getString("store_phone"));
				s.setStoreTime(rs.getString("store_time"));
				s.setStoreInfo(rs.getString("store_info"));
				s.setStoreFacility(rs.getString("store_facility"));
				s.setStoreAddress(rs.getString("store_address"));
				s.setStoreLogo(rs.getString("store_logo"));
				s.setStorePrice(rs.getInt("store_price"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return s;
	}
	
	public List<String> storeImgs(Connection conn, String id) {
		PreparedStatement pstmt = null;
		List<String> imgs = new ArrayList();
		ResultSet rs = null;
		String sql = prop.getProperty("storeImgs");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				imgs.add(rs.getString("store_img"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return imgs;
	}
	
	public StoreSeat storeSeat(Connection conn, String id) {
		PreparedStatement pstmt = null;
		StoreSeat ss = null;
		ResultSet rs = null;
		String sql = prop.getProperty("storeSeat");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ss = new StoreSeat();
				ss.setStoreId(rs.getString("store_id"));
				ss.setCol(rs.getInt("seat_col"));
				ss.setRow(rs.getInt("seat_row"));
				ss.setSeatNum(rs.getString("seat_num"));
				ss.setSeatCheck(rs.getString("seat_check"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return ss;
	}
	
	public int storeFavoriteInsert(Connection conn, String userId, String storeLogo, String storeId, String storeName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("storeFavoriteInsert");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, storeLogo);
			pstmt.setString(3, storeId);
			pstmt.setString(4, storeName);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int storeFavoriteDelete(Connection conn, String userId, String storeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("storeFavoriteDelete");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, storeId);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int storeFavoriteCheck(Connection conn, String userId, String storeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("storeFavoriteCheck");
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, storeId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
		
	}
	
	public List favoriteList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql = prop.getProperty("favoriteList"); 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				List list2 = new ArrayList();
				rs.getString("STORE_ID");
				list2.add(rs.getString("STORE_LOGO"));
				list2.add(rs.getString("STORE_ID"));
				list2.add(rs.getString("STORE_NAME"));
				list.add(list2);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public int favoriteSize(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("favoriteList");
		int favoriteSize = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				favoriteSize++;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return favoriteSize;
	}

	
	
	public List outMoneyList(Connection conn,String userId,outMoneyDB omdb,int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list=new ArrayList();
		String sql=prop.getProperty("selectOML");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				omdb = new outMoneyDB();
				omdb.setUserId("USER_ID");
				omdb.setOmNumber("OUTPUT_NUM");
				omdb.setOmDate(rs.getDate("OMDATE"));
				omdb.setOmNumber(rs.getString("BANK_NUMBER"));
				omdb.setOm(rs.getInt("OM"));
				omdb.setAfterOm(rs.getInt("AFTER_OM"));
				list.add(omdb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int omlCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		String sql=prop.getProperty("omlCount");
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return count;
	}
	
	public Store crystalstore(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		Store s = new Store();
		ResultSet rs = null;
		String sql = prop.getProperty("store");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				s = new Store();
				s.setStoreId(rs.getString("store_id"));
				s.setStoreName(rs.getString("store_name"));
				s.setStorePhone(rs.getString("store_phone"));
				s.setStoreTime(rs.getString("store_time"));
				s.setStoreInfo(rs.getString("store_info"));
				s.setStoreFacility(rs.getString("store_facility"));
				s.setStoreAddress(rs.getString("store_address"));
				s.setStoreLogo(rs.getString("store_logo"));
				s.setStorePrice(rs.getInt("store_price"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return s;
	}
	
	public Store searchName(Connection conn, String storeName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("storeName");
		Store s = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storeName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				s = new Store();
				s.setStoreId(rs.getString("store_id"));
				s.setStoreName(rs.getString("store_name"));
				s.setStorePhone(rs.getString("store_phone"));
				s.setStoreTime(rs.getString("store_time"));
				s.setStoreInfo(rs.getString("store_info"));
				s.setStoreFacility(rs.getString("store_facility"));
				s.setStoreAddress(rs.getString("store_address"));
				s.setStoreLogo(rs.getString("store_logo"));
				s.setStorePrice(rs.getInt("store_price"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return s;
	}
	
	/*
	 * public List<Store> totalSearch(Connection conn, String keyword, int cPage,
	 * int numPerPage) { PreparedStatement pstmt = null; ResultSet rs = null; String
	 * sql = prop.getProperty("totalSearch"); List <Store> list = new ArrayList();
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setString(1,"%"+keyword+"%");
	 * pstmt.setString(2,"%"+keyword+"%"); pstmt.setString(3,"%"+keyword+"%");
	 * pstmt.setString(4,"%"+keyword+"%"); pstmt.setInt(5,(cPage-1)*numPerPage+1);
	 * pstmt.setInt(6,cPage*numPerPage); rs = pstmt.executeQuery(); while(rs.next())
	 * { Store s = new Store( rs.getString("store_id"), rs.getString("store_name"),
	 * rs.getString("store_phone"), rs.getString("store_time"),
	 * rs.getString("store_info"), rs.getString("store_facility"),
	 * rs.getString("store_address"), rs.getString("store_logo"),
	 * rs.getInt("store_price") );
	 * 
	 * list.add(s); } }catch(SQLException e) { e.printStackTrace(); } finally {
	 * close(rs); close(pstmt); }
	 * 
	 * return list; }
	 * 
	 * public int dataCount(Connection conn, String keyword) { PreparedStatement
	 * pstmt = null; ResultSet rs = null; String sql =
	 * prop.getProperty("dataCount"); int result = 0; try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1,"%"+keyword+"%");
	 * pstmt.setString(2,"%"+keyword+"%"); pstmt.setString(3,"%"+keyword+"%");
	 * pstmt.setString(4,"%"+keyword+"%");
	 * 
	 * rs = pstmt.executeQuery(); if(rs.next()) { result = rs.getInt(1); }
	 * }catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); } return result; }
	 * 
	 * public Store store(Connection conn, String id) { PreparedStatement pstmt =
	 * null; Store s = null; ResultSet rs = null; String sql =
	 * prop.getProperty("store"); try { pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, id); rs = pstmt.executeQuery();
	 * 
	 * if(rs.next()) { s = new Store( rs.getString("store_id"),
	 * rs.getString("store_name"), rs.getString("store_phone"),
	 * rs.getString("store_time"), rs.getString("store_info"),
	 * rs.getString("store_facility"), rs.getString("store_address"),
	 * rs.getString("store_logo"), rs.getInt("store_price") ); }
	 * 
	 * } catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); }
	 * 
	 * return s; }
	 * 
	 * public List<String> storeImgs(Connection conn, String id) { PreparedStatement
	 * pstmt = null; List<String> imgs = new ArrayList(); ResultSet rs = null;
	 * String sql = prop.getProperty("storeImgs"); try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, id); rs =
	 * pstmt.executeQuery(); while(rs.next()) { imgs.add(rs.getString("store_img"));
	 * } } catch(SQLException e) { e.printStackTrace(); } finally { close(pstmt); }
	 * return imgs; }
	 * 
	 * public StoreSeat storeSeat(Connection conn, String id) { PreparedStatement
	 * pstmt = null; StoreSeat ss = null; ResultSet rs = null; String sql =
	 * prop.getProperty("storeSeat"); try { pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, id); rs = pstmt.executeQuery(); if(rs.next()) { ss = new
	 * StoreSeat(); ss.setStoreId(rs.getString("store_id"));
	 * ss.setCol(rs.getInt("seat_col")); ss.setRow(rs.getInt("seat_row"));
	 * ss.setSeatNum(rs.getString("seat_num"));
	 * ss.setSeatCheck(rs.getString("seat_check")); } } catch(SQLException e) {
	 * e.printStackTrace(); } finally { close(rs); close(pstmt); }
	 * 
	 * return ss; }
	 * 
	 * public int storeFavoriteInsert(Connection conn, String userId, String
	 * storeLogo, String storeId, String storeName) { PreparedStatement pstmt =
	 * null; int result = 0; String sql = prop.getProperty("storeFavoriteInsert");
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setString(1, userId);
	 * pstmt.setString(2, storeLogo); pstmt.setString(3, storeId);
	 * pstmt.setString(4, storeName); result = pstmt.executeUpdate(); }
	 * catch(SQLException e) { e.printStackTrace(); } finally { close(pstmt); }
	 * 
	 * return result; }
	 * 
	 * public int storeFavoriteDelete(Connection conn, String userId, String
	 * storeId) { PreparedStatement pstmt = null; int result = 0; String sql =
	 * prop.getProperty("storeFavoriteDelete"); try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, userId); pstmt.setString(2,
	 * storeId); result = pstmt.executeUpdate(); } catch(SQLException e) {
	 * e.printStackTrace(); } finally { close(pstmt); }
	 * 
	 * return result; }
	 * 
	 * public int storeFavoriteCheck(Connection conn, String userId, String storeId)
	 * { PreparedStatement pstmt = null; int result = 0; String sql =
	 * prop.getProperty("storeFavoriteCheck"); ResultSet rs = null; try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, userId); pstmt.setString(2,
	 * storeId); rs = pstmt.executeQuery(); if(rs.next()) { result = 1; } }
	 * catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); }
	 * 
	 * return result;
	 * 
	 * }
	 * 
	 * public List favoriteList(Connection conn, String userId) { PreparedStatement
	 * pstmt = null; ResultSet rs = null; List list = new ArrayList(); String sql =
	 * prop.getProperty("store"); //aaaaa try { pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, userId); rs = pstmt.executeQuery(); while(rs.next()) {
	 * List list2 = new ArrayList(); rs.getString("USER_ID");
	 * list2.add(rs.getString("STORE_LOGO")); list2.add(rs.getString("STORE_ID"));
	 * list2.add(rs.getString("STORE_NAME")); list.add(list2); } }
	 * catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); }
	 * 
	 * return list;
	 * 
	 * }
	 * 
	 * public int favoriteSize(Connection conn, String userId) { PreparedStatement
	 * pstmt = null; ResultSet rs = null; String sql =
	 * prop.getProperty("favoriteList"); int favoriteSize = 0; try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, userId); rs =
	 * pstmt.executeQuery(); while(rs.next()) { favoriteSize++; } }
	 * catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); }
	 * 
	 * return favoriteSize; }
	 * 
	 * 
	 * public List outMoneyList(Connection conn,String userId, outMoneyDB omdb) {
	 * PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * prop.getProperty("outMoneyList"); List list = new ArrayList(); try { pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, userId); rs =
	 * pstmt.executeQuery(); System.out.println(rs.next()); while(rs.next()) { omdb
	 * = new outMoneyDB(); omdb.setUserId("USER_ID");
	 * omdb.setOmNumber("OUTPUT_NUM"); omdb.setOmDate(rs.getDate("OMDATE"));
	 * omdb.setOmNumber(rs.getString("BANK_NUMBER")); omdb.setOm(rs.getInt("OM"));
	 * omdb.setAfterOm(rs.getInt("AFTER_OM")); list.add(omdb); } }
	 * catch(SQLException e) { e.printStackTrace(); } finally { close(rs);
	 * close(pstmt); } return list; }
	 * 
	 * public int omlCount(Connection conn) { PreparedStatement pstmt=null;
	 * ResultSet rs=null; int count=0; String sql=prop.getProperty("omlCount"); try
	 * { pstmt=conn.prepareStatement(sql); rs=pstmt.executeQuery(); if(rs.next())
	 * count=rs.getInt(1); }catch(SQLException e) { e.printStackTrace(); }finally {
	 * close(rs); close(pstmt); }return count; }
	 */
//	public List crawl() {
//		
//		List list = new ArrayList();
//		
//		String url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=게임검색";
//		Document doc = null;
//		
//		try {
//			doc = Jsoup.connect(url).get();
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		// 가져올 태그를 가져온다
//		Elements element = doc.select("ol.realtime_srch");
//		
//		Iterator<Element> ie1 = element.select("em.num").iterator();
//		Iterator<Element> ie2 = element.select("span.tit").iterator();
//		
//		while(ie1.hasNext()) {
//			list.add(ie1.next().text() + "\t" + ie2.next().text());
//			System.out.println(list.add(ie1.next().text() + "\t" + ie2.next().text()));
//		}
//		
//		return list;
//	}
	

}





















