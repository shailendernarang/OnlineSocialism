package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.Friend;
import com.ss.SocialistB.model.User;

@Repository(value="friendDao")
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	SessionFactory sessionFactory;
	public List<User> listOfSuggestedUsers(String userName) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		SQLQuery query = session.createSQLQuery("select * from user_table where firstName in"
				+"(select firstName from user_table where firstName!=? minus"
				+"(select fromID from friend where toID=?"
				+"union select toID from friend where fromID=?"
				+"))");
				query.setString(0,userName );
				query.setString(1,userName );
				query.setString(2,userName );
				query.addEntity(User.class);
				List<User> suggestedUser = query.list();
				return suggestedUser;

	}
	public void friendRequest(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.save(friend);
		
	}
	public List<Friend> pendingRequests(String toID) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where toID=? and status='P'");
		query.setString(0, toID);
		return query.list();
	}
	public void updatePendingRequest(Friend friend) {
		Session s = sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A'){
		s.update(friend);
		}
		else
			s.delete(friend);
	}
	public List<String> listOfFriends(String userName) {
		Session session= sessionFactory.getCurrentSession();
		@SuppressWarnings("deprecation")
		SQLQuery query1 = session.createSQLQuery("select fromID from Friend where toID=? and status='A'")
				.addScalar("fromID", StandardBasicTypes.STRING);
				query1.setString(0, userName);
		List<String> list = query1.list();
		System.out.println(list);
		Query query2 = session.createSQLQuery("select toID from Friend where fromID=? and status='A'")
				.addScalar("toID",StandardBasicTypes.STRING);
		query2.setString(0, userName);

		List<String> list1 = query2.list();
		System.out.println(list1);
		list1.addAll(list);
		return list1;
	}
	
}

