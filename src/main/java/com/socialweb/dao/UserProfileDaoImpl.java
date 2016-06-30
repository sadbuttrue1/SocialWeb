package com.socialweb.dao;

import com.socialweb.domain.UserProfile;
import java.sql.SQLException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class UserProfileDaoImpl implements UserProfileDao{

    @Autowired  
    private SessionFactory sessionFactory;  
    
    @Override
    public UserProfile findProfileByUserName(String username) {
        return (UserProfile)sessionFactory.getCurrentSession().getNamedQuery("UserProfile.findByUserName")
                .setParameter("username", username).uniqueResult();
    }

    @Override
    public UserProfile findProfileById(int id) {
        return (UserProfile)sessionFactory.getCurrentSession().getNamedQuery("UserProfile.findByUserId")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public void createNewProfile(UserProfile userProfile){        
        sessionFactory.getCurrentSession().save(userProfile);        
    }
   
}
