package com.socialweb.service;

import com.socialweb.domain.UserData;
import com.socialweb.domain.UserProfile;
import com.socialweb.form.*;
import java.util.List;


public interface UserService {
    public UserData findById(int id);
    
    public UserData findByIdEager(int userId);
    
    public UserData findByProfileLink(String profileLink);
    
    public void updateAll(UserData userData);
    
    public UserProfile findProfileById(int id);
    
    public UserProfile findProfileByUserName(String username);
    
    public int createNewUser(RegistrationForm registration);
    
    public List<UserData> findUsers(SearchForm searchForm);
            
    public boolean findBySameProfileLink(UserData userData);
        
}
