/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.service;

import com.socialweb.domain.UserData;

/**
 *
 * @author alexandr
 */
public interface EmailService {
    public void deleteId(int id);
        
    public void emailProcessingAfterChanging(UserData userData);
}
