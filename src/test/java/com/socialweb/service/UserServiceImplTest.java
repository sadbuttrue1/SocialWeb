package com.socialweb.service;

import com.socialweb.dao.EmailUserDataDAO;
import com.socialweb.dao.UserDataDAO;
import com.socialweb.dao.UserProfileDao;
import com.socialweb.dao.UserProfileRoleDAO;
import com.socialweb.domain.EmailUserData;
import com.socialweb.domain.UserData;
import com.socialweb.domain.UserProfile;
import com.socialweb.domain.UserProfileRole;
import com.socialweb.form.RegistrationForm;
import com.socialweb.form.SearchForm;
import static com.socialweb.utility.Help.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest{

    @Mock
    UserDataDAO userDataDAO;
    @Mock
    UserProfileDao userProfileDAO;
    @Mock
    EmailUserDataDAO emailUserDataDAO;
    @Mock
    UserProfileRoleDAO userProfileRoleDAO;    
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
    public UserServiceImplTest() {
    }

    @Test
    public void testFindById() {
        final int id = 1;
        
        userServiceImpl.findById(id);
        
        verify(userDataDAO).findById(id);
    }

    @Test
    public void testFindBySameProfileLink() {
        final UserData userData = new UserData(1, "Oleg", "Olegov", "1");       
        when(userDataDAO.findByProfileLink(anyString())).thenAnswer(new Answer<UserData>() {

            @Override
            public UserData answer(InvocationOnMock invocation) throws Throwable {
                String profileLink = (String) invocation.getArguments()[0];
                if (profileLink.equals(userData.getProfileLink())) {
                    return userData;
                }
                return null;
            }

        });       

        Assert.assertTrue(!userServiceImpl.findBySameProfileLink(new UserData(1, "Oleg", "Oleg", "1")));
        Assert.assertTrue(!userServiceImpl.findBySameProfileLink(new UserData(1, "Oleg", "Oleg", "foo")));
        Assert.assertTrue(!userServiceImpl.findBySameProfileLink(new UserData(2, "Oleg", "Oleg", "bar")));
        Assert.assertTrue(userServiceImpl.findBySameProfileLink(new UserData(2, "Oleg", "Oleg", "1")));
    }

    @Test
    public void testCreateNewUser() {
        
        when(userProfileDAO.findProfileByUserName("alex93")).thenReturn(null);
        UserProfile userProfile = new UserProfile("alex", "qwerty", null);
        when(userProfileDAO.findProfileByUserName("alex")).thenReturn(userProfile);

        Mockito.doAnswer(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                UserProfile principal = (UserProfile) args[0];
                principal.setId(2);
                return null;
            }
        }).when(userProfileDAO).createNewProfile((UserProfile) Matchers.anyObject());
                        
        when(userProfileRoleDAO.getRoleByName(ROLE_USER)).thenReturn(new UserProfileRole());
        

        Assert.assertEquals(1, userServiceImpl.createNewUser(new RegistrationForm("alex93", "1234", "1234", "Alex", "Alexov", true)));
        Assert.assertEquals(1, userServiceImpl.createNewUser(new RegistrationForm("alex93", "1234", "1234", "Alex", "Alexov", false)));
        Assert.assertEquals(-1, userServiceImpl.createNewUser(new RegistrationForm("alex", "1234", "1234", "Alex", "Alexov", true)));

        verify(userProfileDAO, times(3)).findProfileByUserName(anyString());
        verify(userProfileRoleDAO, times(2)).getRoleByName(ROLE_USER);
        verify(userProfileDAO, times(2)).createNewProfile((UserProfile) anyObject());
        verify(userDataDAO, times(2)).createNewData((UserData) any());
        verify(emailUserDataDAO).createEmail((EmailUserData) any());
    }
    @Ignore
    @Test
    public void testfindUsers(){
        SearchForm searchForm = new SearchForm();
        
        userServiceImpl.findUsers(searchForm);
        
        verify(userDataDAO).findUsers(searchForm);
    }

}
