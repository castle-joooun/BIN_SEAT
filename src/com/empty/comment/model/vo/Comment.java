package com.empty.comment.model.vo;

import java.sql.Date;

public class Comment {

	private int commentNo;
	private int commentLevel;
	private String  commentWriter;
	private String userComment;
	private int commentRef;
	private Date commentDate;
	
 public Comment() {
	// TODO Auto-generated constructor stub
}

public Comment(int commentNo, int commentLevel, String commentWriter, String userComment, int commentRef,
		Date commentDate) {
	super();
	this.commentNo = commentNo;
	this.commentLevel = commentLevel;
	this.commentWriter = commentWriter;
	this.userComment = userComment;
	this.commentRef = commentRef;
	this.commentDate = commentDate;
}

public int getCommentNo() {
	return commentNo;
}

public void setCommentNo(int commentNo) {
	this.commentNo = commentNo;
}

public int getCommentLevel() {
	return commentLevel;
}

public void setCommentLevel(int commentLevel) {
	this.commentLevel = commentLevel;
}

public String getCommentWriter() {
	return commentWriter;
}

public void setCommentWriter(String commentWriter) {
	this.commentWriter = commentWriter;
}

public String getUserComment() {
	return userComment;
}

public void setUserComment(String userComment) {
	this.userComment = userComment;
}

public int getCommentRef() {
	return commentRef;
}

public void setCommentRef(int commentRef) {
	this.commentRef = commentRef;
}

public Date getCommentDate() {
	return commentDate;
}

public void setCommentDate(Date commentDate) {
	this.commentDate = commentDate;
}

@Override
public String toString() {
	return "Comment [commentNo=" + commentNo + ", commentLevel=" + commentLevel + ", commentWriter=" + commentWriter
			+ ", userComment=" + userComment + ", commentRef=" + commentRef + ", commentDate=" + commentDate + "]";
}


 
}
