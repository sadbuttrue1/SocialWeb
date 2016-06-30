/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.dao;

import com.socialweb.domain.EmailUserData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexandr
 */
@Repository
public class EmailUserDataDAOImpl implements EmailUserDataDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void createEmail(EmailUserData email) {
        sessionFactory.getCurrentSession().save(email);
    }

    @Override
    public void deleteId(int id) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM email WHERE email_id = :id LIMIT 1").
               setParameter("id", id).executeUpdate();
    }
    
}
