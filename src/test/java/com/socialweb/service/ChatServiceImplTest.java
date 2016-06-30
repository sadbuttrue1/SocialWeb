package com.socialweb.service;

import com.socialweb.dao.ChatDAO;
import com.socialweb.dao.MessageDAO;
import com.socialweb.domain.Chat;
import com.socialweb.domain.UserData;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceImplTest {

    @Mock
    MessageDAO messageDAO;
    @Mock
    ChatDAO chatDAO;
    @Mock
    UserService userService;
    @InjectMocks
    private ChatServiceImpl chatServiceImpl;

    public ChatServiceImplTest() {
    }

    @Test
    public void testGetAllChatsIsEmpty() {
        int id = 2;
        System.out.println("ChatService.testGetAllChatsIsEmpty");
        /*Во время тестирования DAO проверить, что возращает chatDAO.getAllChats(id),
         если для человека нет чатов. 
         when(chatDAO.getAllChats(id)).thenReturn(null); так не работает
         необходимо измежать NullPointException*/
        when(chatDAO.getAllChats(id)).thenReturn(new ArrayList<Chat>());
        when(userService.findById(anyInt())).thenReturn(new UserData());

        List<UserData> userDatas = chatServiceImpl.getAllChats(id);

        verify(chatDAO).getAllChats(id);
        verify(userService, times(0)).findById(anyInt());
        assertTrue(userDatas.isEmpty());
    }

    @Test
    public void testGetAllChats() {
        int id = 2;
        System.out.println("ChatService.getAllChats");
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat(2, 3));
        chats.add(new Chat(2, 4));
        chats.add(new Chat(5, 2));
        chats.add(new Chat(6, 2));

        when(chatDAO.getAllChats(id)).thenReturn(chats);
        when(userService.findById(anyInt())).thenReturn(new UserData());

        List<UserData> userDatas = chatServiceImpl.getAllChats(id);

        verify(chatDAO).getAllChats(id);
        verify(userService, times(4)).findById(anyInt());
        assertEquals(4, userDatas.size());
    }

    @Test
    public void testCreateChatNotNull() {
        System.out.println("createChatNotNull");
        int senderId = 2;
        int receiverId = 3;        
        Chat chat = new Chat(senderId, receiverId);
        String msg = "Hello World";
        when(chatDAO.findBySenderReceiver(senderId, receiverId)).thenReturn(chat);

        Chat chatRes = chatServiceImpl.createChat(senderId, receiverId, msg);

        verify(chatDAO).findBySenderReceiver(senderId, receiverId);
        verify(messageDAO).createNewMessage(senderId, chat, msg);
        assertEquals(chat, chatRes);
    }
    
    @Test
    public void testCreateChatNull() {
        System.out.println("createChatNull");
        int senderId = 2;
        int receiverId = 3;               
        String msg = "Hello World";
        when(chatDAO.findBySenderReceiver(senderId, receiverId)).thenReturn(null);
        
        Chat chatRes = chatServiceImpl.createChat(senderId, receiverId, msg);

        /*нормально ли сравнивать со значениями возвращаемыми из метода*/
        verify(chatDAO).findBySenderReceiver(senderId, receiverId);
        assertEquals(senderId, chatRes.getFirstPerson());
        assertEquals(receiverId, chatRes.getSecondPerson());
        verify(chatDAO).createNewChat(chatRes);
        verify(messageDAO).createNewMessage(senderId, chatRes, msg);                
    }
    
    @Test
    public void testCreateNewMessage() {
        final int sender = 1;
        final Chat chat = new Chat();
        final String text = "Hello World";
        
        chatServiceImpl.createNewMessage(sender, chat, text);
        
        verify(messageDAO).createNewMessage(sender, chat, text);
    }
    
    @Test
    public void testGetMessagesOnLimit() {
        final int chatId = 1;
        
        chatServiceImpl.getMessagesOnLimit(chatId);
        
        verify(messageDAO).getMessagesOnLimit(chatId);
    }
    
    @Test
    public void testGetLastMessages() {
        final int chatId = 1;
        final long lastUpdate = 139222278989L;
        
        chatServiceImpl.getLastMessages(chatId, lastUpdate);
        
        verify(messageDAO).getLastMessages(chatId, lastUpdate);
    }
    
    @Test
    public void testFindBySenderReceiver() {        
        final int sender = 0;
        final int receiver = 0;
        
        chatServiceImpl.findBySenderReceiver(sender, receiver);
        
        verify(chatDAO).findBySenderReceiver(sender, receiver);
    }
   
    @Test
    public void testFindById() {        
        final int chatId = 0;
        
        chatServiceImpl.findById(chatId);
        
        verify(chatDAO).findById(chatId);
    }

}
