/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.service;

import com.socialweb.dao.EmailUserDataDAO;
import com.socialweb.domain.EmailUserData;
import com.socialweb.domain.UserData;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {
    
    @Mock
    private EmailUserDataDAO emailUserDataDAO;
    @InjectMocks
    private EmailServiceImpl emailServiceImpl;
    
    public EmailServiceImplTest() {
    }
  

    @Test
    public void testEmailProcessingAfterChanging() {
        UserData userData = new UserData(1, "Name", "Surname", "ProfileLink");
        List<EmailUserData> emailUserDatas = new ArrayList<>();
        emailUserDatas.add(new EmailUserData(1, null, "email1@123.ru", true));
        emailUserDatas.add(new EmailUserData(0, null, "email2@123.ru", true));
        emailUserDatas.add(new EmailUserData(0, null, "email3@123.ru", false));
        emailUserDatas.add(new EmailUserData(2, null, "email4@123.ru", false));
        userData.setEmails(emailUserDatas);
        
        emailServiceImpl.emailProcessingAfterChanging(userData);
                
        verify(emailUserDataDAO).deleteId(1);
        verify(emailUserDataDAO).deleteId(0);
        int size = userData.getEmails().size();
        assertEquals(size, 2);
        for(int i = 0; i < size; i++){
            assertEquals(userData.getEmails().get(i).getUserData(), userData);
        }
                
    }
    
}
