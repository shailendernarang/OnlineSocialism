package com.ss.SocialistB.service;

import java.util.List;

import com.ss.SocialistB.model.Friend;
import com.ss.SocialistB.model.User;

public interface FriendService {
	List<User> listOfSuggestedUsers(String userName);
	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String toID);
	void updatePendingRequests(Friend friend);
	List<String> listOfFriends(String userName);
}
