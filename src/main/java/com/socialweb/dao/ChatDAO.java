package com.socialweb.dao;

import com.socialweb.domain.Chat;
import java.util.List;


public interface ChatDAO {  
    public List<Chat> getAllChats(int userId); //получение всех чатов пользователя
    
    public void createNewChat(Chat chat); //создание нового чата
    
    public Chat findBySenderReceiver(int sender, int receiver);
    
    public Chat findById(int chatId);
}
