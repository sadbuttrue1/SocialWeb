package com.socialweb.service;

import com.socialweb.domain.Chat;
import com.socialweb.domain.Message;
import com.socialweb.domain.UserData;
import java.util.List;

public interface ChatService {
    /*возвращать нужно list<UserData>
    сделать это можно, например, подав еще один парамете principalId
    и заносить в list тех у кого principalId != userId*/
   // public List<LightUserData> getAllChats2(int userId);
    
    public List<UserData> getAllChats(int userId);
    //public List<LightUserData> getAllChats(int userId);
    
    /*message здесь тоже создается. Ведь так?*/
    public Chat createChat(int sender, int receiver, String text);
    
    /*вместо Chat объекта лучше посылать chatId, у меня в 
    запрсах приходит id, а не объект*/
    public void createNewMessage(int sender, Chat chat, String text);
    
    public List<Message> getMessagesOnLimit(int chatId);
    

    /*сюда как раз нужно подать long lastUpdate и вернуть сообщения
    которые строго позже*/ 
    public List<Message> getLastMessages(int chatId, long lastUpdate);
    
    public Chat findBySenderReceiver(int sender, int receiver);
    
    public Chat findById(int chatId);
}
