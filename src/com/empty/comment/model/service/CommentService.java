package com.empty.comment.model.service;

import static com.empty.common.JDBCTemplate.close;
import static com.empty.common.JDBCTemplate.commit;
import static com.empty.common.JDBCTemplate.getConnection;
import static com.empty.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.empty.comment.dao.CommentDao;
import com.empty.comment.model.vo.Comment;
public class CommentService {
	 private CommentDao dao =new CommentDao();
	 
//public List<Comment> searchComment (int cPage,int numPerPage){
//	Connection conn = getConnection();
//	List<Comment> list = dao.searchComment(conn,cPage,numPerPage);
//	close(conn);
//	return list;
//}
public int commentCount() {
		Connection conn=getConnection();
		int count=dao.commentCount(conn);
		close(conn);
		return count;
	
}
public int insertComment(Comment c) {
		Connection conn =getConnection();
		int result=dao.insertComment(conn,c);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;

}
public List<Comment> selectComment() {
	 Connection conn= getConnection();
	 List<Comment> commentList = dao.selectComment(conn);
	 close(conn);
	return commentList;
}
public int updateComment(int no, String userComment) {
	Connection conn = getConnection();

	int result=dao.updateComment(conn,no,userComment);
	System.out.println(no+userComment +"service");
	if(result>0) commit(conn);
	else rollback(conn);
	close(conn);

	return result;
}
public int selCommNo(int no) {
	Connection conn = getConnection();
	 int result = dao.selCommNo(conn,no);
	 if(result>0) commit(conn);
	 else rollback(conn);
	 close(conn);
	return result;
}
public int deleteComment(int no) {
	 Connection conn = getConnection();
	 int result = dao.delectComment(conn,no);
	 if(result>0) commit(conn);
	 else rollback(conn);
	 close(conn);
	System.out.println("Servic"+no);
	return result;
}



}