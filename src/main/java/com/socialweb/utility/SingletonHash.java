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
public class SingletonHash {
    private SingletonHash(){}
    
    private static class SingletonHashHolder{
        private static final SingletonHash INSTANCE = new SingletonHash();
    }
    
    public static SingletonHash getInstance(){
        return SingletonHashHolder.INSTANCE;
    }
        
    
    public String hashTime(){
        //а как?        
        return ((Long)System.currentTimeMillis()).toString();
    }
}
