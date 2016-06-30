package com.socialweb.dao;

import com.socialweb.domain.Chat;
import com.socialweb.domain.Message;
import java.util.List;

public interface MessageDAO {
    public void createNewMessage(int sender, Chat chat, String text); //создание нового сообщения
    
    public List<Message> getMessagesOnLimit(int chatId); // получение сообщений 
    
    public List<Message> getLastMessages(int chatId, long lastUpdate); // получение последних сообщений
}
