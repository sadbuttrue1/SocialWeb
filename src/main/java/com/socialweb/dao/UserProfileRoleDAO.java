package com.socialweb.dao;

import com.socialweb.domain.UserProfileRole;

public interface UserProfileRoleDAO {
    public UserProfileRole getRoleByName(String role);
    
    public void createRole(UserProfileRole role);
}
