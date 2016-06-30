/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.service;

import com.socialweb.domain.UserData;
import java.util.List;

/**
 *
 * @author alexandr
 */
public interface FriendService {
    public void addFriend(int userId, int friendiId);
    
    public List<UserData> friendsConfirmList(final int userId);
    
    public List<UserData> friendsList(final int userId);
    
    public void friendConfirm(int userId, int friendiId);
    
    public void friendDel(int userId, int friendiId);
    
    public int friendStatus(int userId, int friendId);
    
//    public int isFriend(UserData user, int friend_id);
}
