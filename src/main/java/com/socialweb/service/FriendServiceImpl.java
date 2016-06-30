package com.socialweb.service;

import com.socialweb.dao.FriendDAO;
import com.socialweb.domain.UserData;
import com.socialweb.utility.RatioFriendsClass;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDAO friendDAO;

    @Transactional
    @Override
    public void addFriend(int userId, int friendId) {
        friendDAO.addFriend(userId, friendId, RatioFriendsClass.WAITING);
    }

    @Transactional
    @Override
    public List<UserData> friendsConfirmList(final int userId) {
        return friendDAO.friendsConfirmList(userId);
    }

    @Transactional
    @Override
    public List<UserData> friendsList(final int userId) {
        return friendDAO.friendsList(userId);
    }

    @Transactional
    @Override
    public void friendConfirm(int userId, int friendiId) {
        friendDAO.updateStatus(userId, friendiId, RatioFriendsClass.IS_FRIEND);
    }

    @Transactional
    @Override
    public void friendDel(int userId, int friendiId) {
        friendDAO.deleteFriend(userId, friendiId);
    }
    
    @Transactional
    @Override
    public int friendStatus(int userId, int friendId) {
        if (userId == friendId) {
            return RatioFriendsClass.HIMSELF;
        }
        int status = friendDAO.friendStatus(userId, friendId);
        if (status == 0) {
            return RatioFriendsClass.NOTFRIENDS;
        }
        return status;
    }

}
