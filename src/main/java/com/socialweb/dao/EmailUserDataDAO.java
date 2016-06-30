/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.dao;

import com.socialweb.domain.EmailUserData;

/**
 *
 * @author alexandr
 */
public interface EmailUserDataDAO {
    public void createEmail(EmailUserData email);
    
    public void deleteId(int id);
}
