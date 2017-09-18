package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Forum;

public interface ForumDAO {

	public boolean createForum(Forum forum);
	public boolean editForum(Forum forum);
	public boolean deleteForum(Integer forumID);
	public Forum getForum(Integer forumID);
	public List<Forum> getAllForums();
	public boolean approveForum(Forum forum);
}
