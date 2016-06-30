package com.socialweb.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.socialweb.domain.UserProfile;
import com.socialweb.domain.UserProfileRole;
import com.socialweb.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager{	  
    @Autowired 
    private UserService userDataService;
    
    @Override
    public User getUser(String username) throws UsernameNotFoundException {    
        UserProfile userEntity = userDataService.findProfileByUserName(username);
        if (userEntity == null)
          throw new UsernameNotFoundException("user not found");
       
        /*Fill UserDetails*/
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(UserProfileRole role : userEntity.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        User user = new User(userEntity.getId(), username, userEntity.getPassword(), authorities, true,
          true, true, true);
        return user;
    }  
}
