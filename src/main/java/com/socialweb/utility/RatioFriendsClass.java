/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socialweb.utility;

/**
 *
 * @author alexandr
 */
public class RatioFriendsClass {
    public static final int HIMSELF = 1;
    public static final int IS_FRIEND = 2;
    public static final int WAITING = 3;    
    public static final int NOTFRIENDS = 4;
    public static final int DECIDES = 5;
    
    public int getHIMSELF(){
        return HIMSELF;
    }
    
    public int getIS_FRIEND(){
        return IS_FRIEND;
    }
    
    public int getWAITING(){
        return WAITING;
    }
    
    public int getNOTFRIENDS(){
        return NOTFRIENDS;
    }
    
    public int getDECIDES(){
        return DECIDES;
    }
}
