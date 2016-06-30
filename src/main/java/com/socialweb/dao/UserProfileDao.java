package com.socialweb.dao;

import com.socialweb.domain.UserProfile;

public interface UserProfileDao {
    public UserProfile findProfileById(int id);
    
    public UserProfile findProfileByUserName(String username);
    
    public void createNewProfile(UserProfile userProfile);
        
}
