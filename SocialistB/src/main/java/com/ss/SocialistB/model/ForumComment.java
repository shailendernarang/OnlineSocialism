package com.ss.SocialistB.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class ForumComment implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int forumCommentID;
	private int forumID;
	private String forumComment;
	private Date forumCommentDate;
	private int userID;
	private String userName;
	public int getForumCommentID() {
		return forumCommentID;
	}
	public void setForumCommentID(int forumCommentID) {
		this.forumCommentID = forumCommentID;
	}
	public int getForumID() {
		return forumID;
	}
	public void setForumID(int forumID) {
		this.forumID = forumID;
	}
	public String getForumComment() {
		return forumComment;
	}
	public void setForumComment(String forumComment) {
		this.forumComment = forumComment;
	}
	public Date getForumCommentDate() {
		return forumCommentDate;
	}
	public void setForumCommentDate(Date forumCommentDate) {
		this.forumCommentDate = forumCommentDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
