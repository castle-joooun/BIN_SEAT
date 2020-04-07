
package com.empty.comment.dao;

import static com.empty.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.empty.comment.model.vo.Comment;

public class CommentDao {
	private Properties prop  =new Properties();
	
	public CommentDao() {
	
		try {
			String path=CommentDao.class.getResource("/sql/comment/comment.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();	
			
		}
		
	}

	public List<Comment> searchComment(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		List<Comment>list =new ArrayList();
		String sql=prop.getProperty("searchComment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage); //시작데이터번호 
			pstmt.setInt(2, cPage*numPerPage); // 끝 데이터 번호
			rs=pstmt.executeQuery();
	
			while(rs.next()) {
				Comment comment =new Comment();
				comment.setCommentNo(rs.getInt("COMMENT_NO"));
				comment.setCommentLevel(rs.getInt("COMMENT_LEVEL"));
				comment.setCommentWriter(rs.getString("COMMENT_WRITER"));
				comment.setUserComment(rs.getString("USER_COMMENT"));
				comment.setCommentRef(rs.getInt("COMMENT_REF"));
				comment.setCommentDate(rs.getDate("COMMENT_DATE"));
			
				
				list.add(comment);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();	
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
		
	}
	
	public int commentCount(Connection conn) {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			int count=0;
			String sql=prop.getProperty("commentCount");
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) count=rs.getInt(1);
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {		
				close(rs);
				close(pstmt);
			}
		return count;
	}

	public int insertComment(Connection conn, Comment c) {
		PreparedStatement pstmt =null;
		int result=0;
		String sql=prop.getProperty("insertComment");
	
		try {
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1, c.getCommentLevel());
			pstmt.setString(2,c.getCommentWriter());
			pstmt.setString(3,c.getUserComment());
			pstmt.setString(4, c.getCommentRef()==0?null:String.valueOf(c.getCommentRef()));
		
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		
		return result;
		
	}

	public int updateComment(Connection conn, int no, String userComment) {
		 PreparedStatement pstmt=null;
		 int result=0;
		 String sql=prop.getProperty("updateComment");
		 
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1, userComment);
			 pstmt.setInt(2, no);
			 result=pstmt.executeUpdate();
		
			 System.out.println("댓글수정 번호dao"+ no);
			 System.out.println("댓글수정 댓글dao"+ userComment);
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }finally {
			 close(pstmt);
		 }
		 
		return result;
	}
	
	public int delectComment(Connection conn, int no) {
		PreparedStatement pstmt =null;
		int result=0;
		String sql=prop.getProperty("deleteComment");
		try {
		
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
	
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		System.out.println("dao값2"+ no);
		return result;
	}
	
	}

