package com.ss.SocialistB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.FriendDAO;
import com.ss.SocialistB.model.Friend;
import com.ss.SocialistB.model.User;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	FriendDAO friendDAO;
	public List<User> listOfSuggestedUsers(String userName) {
		return friendDAO.listOfSuggestedUsers(userName);
	}

	public void friendRequest(Friend friend) {
		friendDAO.friendRequest(friend);
		
	}

	public List<Friend> pendingRequests(String toID) {
		return friendDAO.pendingRequests(toID);
	}

	public void updatePendingRequests(Friend friend) {
		friendDAO.updatePendingRequest(friend);
		
	}

	public List<String> listOfFriends(String userName) {
		return friendDAO.listOfFriends(userName);
	}

}
