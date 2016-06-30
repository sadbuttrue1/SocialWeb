package com.socialweb.dao;

import com.socialweb.domain.UserData;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dataTest.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
@ActiveProfiles("testH2")
public class UserDataDAOImplTest {
        
    @Autowired
    @Qualifier("userDataDAOImpl")
    private UserDataDAO userDataDAO;

    public UserDataDAOImplTest() {
    }

    @Test
    public void testFindById() {
        UserData userData = userDataDAO.findById(1);
        assertNotNull(userData); 
        assertEquals(userData.getProfileLink(), "ivanS");
    }

    @Test
    public void testFindByIdWhereTime() {
        UserData userData = userDataDAO.findById(1);
        assertNotNull(userData); 
        assertEquals(userData.getProfileLink(), "ivanS");
    }
}
