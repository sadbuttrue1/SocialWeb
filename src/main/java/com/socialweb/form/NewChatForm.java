/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alexandr
 */
public class NewChatForm {
    
    @NotBlank(message = "label.empty")
    private int addresseeUserId;
    
    @NotBlank(message = "label.empty")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public int getAddresseeUserId() {
        return addresseeUserId;
    }

    public void setAddresseeUserId(int addresseeUserId) {
        this.addresseeUserId = addresseeUserId;
    }

}
