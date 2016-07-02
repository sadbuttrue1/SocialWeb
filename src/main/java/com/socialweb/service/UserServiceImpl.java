package com.socialweb.service;

import static com.socialweb.utility.Help.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataDAO userDataDAO;
        
    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private EmailUserDataDAO emailDAO;
    
    @Autowired
    private UserProfileRoleDAO userProfileRoleDAO;

    public UserServiceImpl(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }
    
    
    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDataDAO userDataDAO, UserProfileDao userProfileDao, EmailUserDataDAO emailDAO, UserProfileRoleDAO userProfileRoleDAO) {
        this.userDataDAO = userDataDAO;
        this.userProfileDao = userProfileDao;
        this.emailDAO = emailDAO;
        this.userProfileRoleDAO = userProfileRoleDAO;
    }
    
    
    
    @Transactional
    @Override
    public UserData findById(int id) {
        return userDataDAO.findById(id);
    }
    
//    @Transactional
//    @Override
//    public LightUserData findLightUserDataById(int id) {
//        return lightUserDataDAO.findById(id);
//    }

    @Transactional
    @Override
    public void updateAll(UserData userData) {
        userDataDAO.updateAll(userData);
    }

    @Transactional
    @Override
    public UserProfile findProfileById(int id) {
        return userProfileDao.findProfileById(id);
    }

    @Transactional
    @Override
    public UserProfile findProfileByUserName(String username) {
        return userProfileDao.findProfileByUserName(username);
    }

    @Transactional
    @Override
    public int createNewUser(RegistrationForm registration) {
        //как заставить
        int status = 1;
        try {
            if (userProfileDao.findProfileByUserName(registration.getEmail()) != null) {
                return -1;
            }    
            
            // присвоение новому пользователю стандартной роли
            Set<UserProfileRole> userRoles = new HashSet<>();
            userRoles.add(userProfileRoleDAO.getRoleByName(ROLE_USER));
            
            // создание нового профиля
            UserProfile userProfile = new UserProfile(registration.getEmail(), registration.getPassword(), userRoles);
            userProfileDao.createNewProfile(userProfile);
            
            // создание информации о пользователе
            UserData userData = new UserData(userProfile.getId(), registration.getName(),
                    registration.getSurname(), userProfile.getId().toString());
            userDataDAO.createNewData(userData);
            if (registration.isDisplayEmail()) {
                EmailUserData email = new EmailUserData(userData, userProfile.getUsername());
                emailDAO.createEmail(email);
            }
            return status;
        } catch (Exception e) {
            status = -2;
            return status;
        }
    }

    @Transactional
    @Override
    public List<UserData> findUsers(SearchForm searchForm) {
        return userDataDAO.findUsers(searchForm);
    }

    @Transactional
    @Override
    public UserData findByProfileLink(String profileLink) {
        return userDataDAO.findByProfileLink(profileLink);
    }

    @Transactional
    @Override
    public boolean findBySameProfileLink(UserData userData) {
        UserData ud = userDataDAO.findByProfileLink(userData.getProfileLink());
        return ud != null && ud.getId() != userData.getId();

    }

    @Transactional
    @Override
    public UserData findByIdEager(int userId) {
        return userDataDAO.findByIdEager(userId);
    }

}
