package com.socialweb.service;

import com.socialweb.dao.FriendDAO;
import com.socialweb.utility.RatioFriendsClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FriendServiceImplTest {
    
    @Mock
    private FriendDAO friendDAO;
    @InjectMocks
    private FriendServiceImpl friendServiceImpl;
    
    public FriendServiceImplTest() {
    }

    @Test
    public void testFriendStatusHimself() {
        final int id = 1;
        
        int res = friendServiceImpl.friendStatus(id, id);
        
        assertEquals(res, RatioFriendsClass.HIMSELF);
        verify(friendDAO, times(0)).friendStatus(anyInt(), anyInt());
    }
    
    @Test
    public void testFriendStatusNotFriends() {
        final int userId = 1;
        final int friendId = 2;
        when(friendDAO.friendStatus(userId, friendId)).thenReturn(0);
        
        int res = friendServiceImpl.friendStatus(userId, friendId);
        
        assertEquals(res, RatioFriendsClass.NOTFRIENDS);
        verify(friendDAO, times(1)).friendStatus(userId, friendId);
    }    
    
    @Test
    public void testFriendStatus() {
        final int userId = 1;
        final int friendId = 2;
        when(friendDAO.friendStatus(userId, friendId)).thenReturn(RatioFriendsClass.IS_FRIEND);
        
        int res = friendServiceImpl.friendStatus(userId, friendId);
        
        assertEquals(res, RatioFriendsClass.IS_FRIEND);
        verify(friendDAO, times(1)).friendStatus(userId, friendId);
    }
    
}
