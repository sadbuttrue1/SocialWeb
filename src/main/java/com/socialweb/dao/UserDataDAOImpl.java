package com.socialweb.dao;

import com.socialweb.domain.UserData;
import com.socialweb.form.SearchForm;
import com.socialweb.query.QueryBuilder;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataDAOImpl implements UserDataDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private QueryBuilder queryBuilder;
    
    @Override
    public UserData findById(int id) {
        return (UserData)sessionFactory.getCurrentSession().getNamedQuery("UserData.findById").
                setParameter("user_id", id).uniqueResult();
    }

    @Override
    public void updateAll(UserData userData) {
        sessionFactory.getCurrentSession().update(userData);
    }

    @Override
    public void createNewData(UserData userData) {        
        sessionFactory.getCurrentSession().save(userData);
    }  
    
    @Override
    public UserData findByProfileLink(String profileLink) {
        return (UserData)sessionFactory.getCurrentSession().getNamedQuery("UserData.findByProfileLink").
                setParameter("profileLink", profileLink).uniqueResult();
    }

    @Override
    public UserData findByIdEager(int userId) {
        return (UserData)sessionFactory.getCurrentSession().getNamedQuery("UserData.findByIdEager").
                setParameter("user_id", userId).uniqueResult();
    }

    @Override
    public List<UserData> findUsers(SearchForm searchForm) {
         return queryBuilder.buildSearchQuery(searchForm);
    }
}
