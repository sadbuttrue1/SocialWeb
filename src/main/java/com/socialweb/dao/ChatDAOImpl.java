package com.socialweb.dao;

import com.socialweb.domain.Chat;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO{

    @Autowired
    MessageDAO messageDAO;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Chat> getAllChats(int userId) {
        return (List<Chat>)sessionFactory.getCurrentSession().getNamedQuery("Chat.getAllChats")
                .setParameter("user_id", userId).list();
    }

    @Override
    public void createNewChat(Chat chat) {                
        sessionFactory.getCurrentSession().save(chat);                  
    }

    @Override
    public Chat findBySenderReceiver(int sender, int receiver) {
        return (Chat)sessionFactory.getCurrentSession().getNamedQuery("Chat.getChat").
                setParameter("sender", sender).setParameter("receiver", receiver).uniqueResult();        
    }

    @Override
    public Chat findById(int chatId) {
        return (Chat)sessionFactory.getCurrentSession().createQuery("SELECT c FROM"
                + " Chat c WHERE c.Id = :id").setParameter("id", chatId).uniqueResult();
    }
}
