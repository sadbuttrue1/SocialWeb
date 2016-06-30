package com.socialweb.dao;

import com.socialweb.domain.UserProfileRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRoleDAOImpl implements UserProfileRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public UserProfileRole getRoleByName(String role) {
       return (UserProfileRole)sessionFactory.getCurrentSession().getNamedQuery("UserProfileRole.findRoleByName")
               .setParameter("role", role).uniqueResult();
    }

    @Override
    public void createRole(UserProfileRole role) {
        sessionFactory.getCurrentSession().save(role);
    }
    
}
