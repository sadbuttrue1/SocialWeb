/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.dao;

import com.socialweb.domain.Friend;
import com.socialweb.domain.UserData;
import java.util.List;

/**
 *
 * @author alexandr
 */
public interface FriendDAO {
    public void addFriend(int userId, int friendId, int status);
    
    public void updateStatus(int userId, int friendId, int newStatus);
    
    public void deleteFriend(int userId, int friendId);
    
    public List<UserData> friendsConfirmList(final int userId);
    
    public List<UserData> friendsList(final int userId);
    
    public int friendStatus(int userId, int friendId);
}
