/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.dao;

import com.socialweb.domain.UserData;
import com.socialweb.form.SearchForm;
import java.util.List;

public interface UserDataDAO {

    public UserData findById(int id);
    
    public UserData findByIdEager(int userId);
    
    public UserData findByProfileLink(String profileLink);

    public void updateAll(UserData userData);
    
    public void createNewData(UserData userData);
    
    public List<UserData> findUsers(SearchForm searchForm);
}
