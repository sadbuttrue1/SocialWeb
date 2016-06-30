/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.dao;

import com.socialweb.domain.Friend;
import com.socialweb.domain.UserData;
import com.socialweb.utility.RatioFriends;
import com.socialweb.utility.RatioFriendsClass;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexandr
 */
@Repository
public class FriendDAOImpl implements FriendDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addFriend(int userId, int friendId, int status) {
        sessionFactory.getCurrentSession().save(new Friend(userId, friendId, status));
    }

    @Override
    public void updateStatus(int userId, int friendId, int status) {
        sessionFactory.getCurrentSession().getNamedQuery("Friend.updateStatus").
                setParameter("newStatus", status).
                setParameter("friendId", friendId).setParameter("userId", userId).executeUpdate();
    }

    @Override
    public void deleteFriend(int userId, int friendId) {
        sessionFactory.getCurrentSession().getNamedQuery("Friend.deleteByUserFriend").
                setParameter("userId", userId).setParameter("friendId", friendId).executeUpdate();
    }

    @Override
    public List<UserData> friendsConfirmList(int userId) {
        return sessionFactory.getCurrentSession().getNamedQuery("Friend.Confirm").
                setParameter("userId", userId).setParameter("statusWaiting", RatioFriendsClass.WAITING).list();
    }

    @Override
    public List<UserData> friendsList(int userId) {
         return sessionFactory.getCurrentSession().getNamedQuery("Friend.AllFriend").
                setParameter("userId", userId).setParameter("statusIsFriend", RatioFriendsClass.IS_FRIEND).list();
    }

    @Override
    public int friendStatus(int userId, int friendId) {
        return (Integer)sessionFactory.getCurrentSession().getNamedQuery("Friend.status").
                setParameter("userId", userId).setParameter("friendId", friendId).uniqueResult();
    }
    
    
    
}
