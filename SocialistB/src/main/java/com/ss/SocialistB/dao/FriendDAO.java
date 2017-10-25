package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Friend;
import com.ss.SocialistB.model.User;

public interface FriendDAO {
	
	List<User> listOfSuggestedUsers(String userName);
	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String toID);
	void updatePendingRequest(Friend friend);
	List<String> listOfFriends(String userName); 
	
}
