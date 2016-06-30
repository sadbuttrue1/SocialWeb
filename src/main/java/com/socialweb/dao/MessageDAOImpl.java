package com.socialweb.dao;

import com.socialweb.domain.Chat;
import com.socialweb.domain.Message;
import com.socialweb.utility.Help;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ChatDAO chatDAO;
    
    @Override
    public void createNewMessage(int sender, Chat chat, String text) {        
        Message message = new Message(sender, chat, new Date().getTime(), text);
        sessionFactory.getCurrentSession().save(message);
    }

    @Override
    public List<Message> getMessagesOnLimit(int chatId) {
        return (List<Message>)sessionFactory.getCurrentSession().getNamedQuery("Message.getMessagesOnLimit")
                .setParameter("chatId", chatId).setMaxResults(Help.maxMessages).list();
    }

    @Override
    public List<Message> getLastMessages(int chatId, long lastUpdate) {
        return (List<Message>)sessionFactory.getCurrentSession().getNamedQuery("Message.getLastMessages")
                .setParameter("chatId", chatId).setParameter("lastUpdate", lastUpdate)
                .list();
    }
    
}
