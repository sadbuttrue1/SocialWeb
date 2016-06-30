/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.service;

import com.socialweb.dao.EmailUserDataDAO;
import com.socialweb.domain.EmailUserData;
import com.socialweb.domain.UserData;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexandr
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailUserDataDAO emailDAO;

    @Transactional
    @Override
    public void deleteId(int id) {
        emailDAO.deleteId(id);
    }

    @Transactional
    @Override
    public void emailProcessingAfterChanging(UserData userData) {        
        for (int i = 0; i < userData.getEmails().size(); i++) {
            EmailUserData emailUserData = userData.getEmails().get(i);
            if (emailUserData.isRemove()) {
                emailDAO.deleteId(emailUserData.getId());
                userData.getEmails().remove(i);
                i--;//знаю
            } else if (emailUserData.getId() == 0) {
                EmailUserData newEmail = new EmailUserData(userData, emailUserData.getEmail());
                userData.getEmails().set(i, newEmail);
            } else {               
                emailUserData.setUserData(userData);
            }
        }
    }

}
