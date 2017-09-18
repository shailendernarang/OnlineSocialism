package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.ForumComment;

public interface ForumCommentDAO {

	public boolean addForumComment(ForumComment forumComment);
	public boolean editForumComment(ForumComment forumComment);
	public boolean deleteForumComment(Integer forumCommentID);
	public ForumComment getForumComment(Integer forumCommentID);
	public List<ForumComment> getAllForumComments();
}
