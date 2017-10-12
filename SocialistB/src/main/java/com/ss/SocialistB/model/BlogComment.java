package com.ss.SocialistB.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class BlogComment implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogCommentID;
	@ManyToOne
	@JoinColumn(name="firstName")
	private User postedBy;
	private String blogCommentText;
	private Date blogCommentDate;
	@ManyToOne
	@JoinColumn(name="blogID")
	private Blog blog;
	public int getBlogCommentID() {
		return blogCommentID;
	}
	public void setBlogCommentID(int blogCommentID) {
		this.blogCommentID = blogCommentID;
	}
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
	public String getBlogCommentText() {
		return blogCommentText;
	}
	public void setBlogCommentText(String blogCommentText) {
		this.blogCommentText = blogCommentText;
	}
	public Date getBlogCommentDate() {
		return blogCommentDate;
	}
	public void setBlogCommentDate(Date blogCommentDate) {
		this.blogCommentDate = blogCommentDate;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}