package com.ss.SocialistB.service;

import java.util.List;

import com.ss.SocialistB.model.BlogComment;

public interface BlogCommentService {

		public boolean addBlogComment(com.ss.SocialistB.model.BlogComment blogComment);
		public List<BlogComment> getBlogComments(int blogId);
		BlogComment getBlogComment(int blogID);

}
