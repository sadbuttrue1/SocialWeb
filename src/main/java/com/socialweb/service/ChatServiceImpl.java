package com.socialweb.service;

import com.socialweb.dao.ChatDAO;
import com.socialweb.dao.MessageDAO;
import com.socialweb.domain.Chat;
import com.socialweb.domain.Message;
import com.socialweb.domain.UserData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    private ChatDAO chatDAO;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public List<UserData> getAllChats(int userId) {
        List<Chat> chats = chatDAO.getAllChats(userId);
        List<UserData> userDatas = new ArrayList<>();
        for (Chat c : chats) {
            int adresseeId = c.getFirstPerson() == userId ? c.getSecondPerson() : c.getFirstPerson();
            userDatas.add(userService.findById(adresseeId));
        }
        return userDatas;
    }

    @Transactional
    @Override
    public Chat createChat(int sender, int receiver, String text) {
        Chat chat = chatDAO.findBySenderReceiver(sender, receiver);

        if (chat == null) {
            chat = new Chat(sender, receiver);
            chatDAO.createNewChat(chat);
        }

        messageDAO.createNewMessage(sender, chat, text);

        return chat;
    }

    @Transactional
    @Override
    public void createNewMessage(int sender, Chat chat, String text) {
        messageDAO.createNewMessage(sender, chat, text);
    }

    @Transactional
    @Override
    public List<Message> getMessagesOnLimit(int chatId) {
        return messageDAO.getMessagesOnLimit(chatId);
    }

    @Transactional
    @Override
    public List<Message> getLastMessages(int chatId, long lastUpdate) {
        return messageDAO.getLastMessages(chatId, lastUpdate);
    }

    @Transactional
    @Override
    public Chat findBySenderReceiver(int sender, int receiver) {
        return chatDAO.findBySenderReceiver(sender, receiver);
    }

    @Transactional
    @Override
    public Chat findById(int chatId) {
        return chatDAO.findById(chatId);
    }

}
