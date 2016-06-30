package com.socialweb.utility;

public class Help {
    
    public final static int maxElemPage = 2;
    public final static int maxMessages = 20;
    
    public final static String ROLE_USER = "ROLE_USER";
    
    public final static String PATH_ROOT = "../webapps/ROOT/avatar/";
        
    
    public static boolean isReally(String string){
        if(string == null || string.trim().equals(""))
            return false;
        return true;
    }
    
    public static String nameAvatar(String originalName){
        
        String extension = originalName.substring(originalName.lastIndexOf('.'), originalName.length());
        return SingletonHash.getInstance().hashTime() + extension;
    }
        
}
