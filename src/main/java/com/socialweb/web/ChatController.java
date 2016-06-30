package com.socialweb.web;

import com.socialweb.domain.Chat;
import com.socialweb.domain.Message;
import com.socialweb.domain.UserData;
import com.socialweb.form.NewChatForm;
import com.socialweb.security.User;
import com.socialweb.service.ChatService;
import com.socialweb.service.FriendService;
import com.socialweb.service.UserService;
import com.socialweb.utility.MessageJSON;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(method = RequestMethod.GET)
    public String showChat(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserData> userDatas = chatService.getAllChats(principal.getId());
        model.addAttribute("UserDatas", userDatas);
        return "chat";
    }

    @RequestMapping(value = "/createChat", method = RequestMethod.GET)
    public String createFormNewChat(Model model,
            @RequestParam("isPage") int isPage) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("newChatForm", new NewChatForm());
        createChatModel(model, isPage, principal.getId());
        return "newChat";
    }

    @RequestMapping(value = "/createChat", method = RequestMethod.POST)
    public String createChat(@RequestParam("isPage") int isPage, Model model,
            final @ModelAttribute("newChatForm") NewChatForm newChat) {
//        if (true) {
//            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            createChatModel(model, isPage, principal.getId());
//            return "newChat";
//        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        chatService.createChat(principal.getId(), newChat.getAddresseeUserId(), newChat.getMessage());
        return "redirect:/chat";
    }

    //данный метод, если не находит chat то перенаправляет на создание chat
    @RequestMapping(value = "/showMessage", method = RequestMethod.GET)
    public String showMessage(Model model, @RequestParam("addresseeId") int addresseeId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //послать все сообщения принципала и юзера адресата
        //сообщение последние 100(магическое число), например
        Chat chat = chatService.findBySenderReceiver(principal.getId(), addresseeId);
        if (chat != null) {
            List<Message> messages = chatService.getMessagesOnLimit(chat.getId());
            model.addAttribute("messages", messages);
            //посылаем двух юзеров, которые перемисываются, что бы занести мета информацию о них
            //и поднимать ее по id автора
            model.addAttribute("principalUser", userService.findById(principal.getId()));
            model.addAttribute("addresseeUser", userService.findById(addresseeId));
            //посылаем последнее обнавление в long
            long lastUpdate = messages.get(messages.size() - 1).getMessageDate();
            model.addAttribute("lastUpdate", lastUpdate);
            model.addAttribute("chatId", chat.getId());
        } else {
            return "redirect:/chat/createChat?isPage=" + addresseeId;
        }
        return "showMessage";
    }

    @RequestMapping(value = "/sendMessageChat/{chatId}", method = RequestMethod.GET)
    public @ResponseBody
    String sendMessageChat(@RequestParam("text") String text,
            @PathVariable("chatId") int chatId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //происходит сохранение сообщения
        chatService.createNewMessage(principal.getId(), chatService.findById(chatId), text);
        return "good";
    }

    @RequestMapping(value = "/refreshMessages", method = RequestMethod.GET)
    public @ResponseBody
    List<MessageJSON> refreshMessages(@RequestParam("lastUpdate") long lastUpdate,
            @RequestParam("chatId") int chatId) {
        //вернуть все сообщения, которые строго позже этого времени
        //в формате json
        List<Message> messages = chatService.getLastMessages(chatId, lastUpdate);
        return convertToJSON(messages);
    }

    @RequestMapping(value = "/sendMessagePage", method = RequestMethod.GET)
    public String sendMessagePage(Model model, @RequestParam int addresseeId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //если чат уже существует, то перенапрваить на showMessage
        Chat chat = chatService.findBySenderReceiver(principal.getId(), addresseeId);
        if (chat != null) {
            return "redirect:/chat/showMessage?addresseeId=" + addresseeId;
        } else {
            //иначе перенправить на createChat
            return "redirect:/chat/createChat?isPage=" + addresseeId;
        }
    }

    private List<MessageJSON> convertToJSON(List<Message> messages) {
        List<MessageJSON> messagesJSON = new ArrayList<>();
        for (Message message : messages) {
            MessageJSON messageJson = new MessageJSON();
            messageJson.setAuthorId(message.getSender());
            messageJson.setMessageDate(message.getMessageDate());
            messageJson.setText(message.getText());
            messagesJSON.add(messageJson);
        }
        return messagesJSON;
    }

    private void createChatModel(Model model, int isPage, int principalId) {
        model.addAttribute("isPage", isPage);
        if (isPage == 0) {
            //UserData user = userService.findById(principalId);
            model.addAttribute("friends", friendService.friendsList(principalId));
            model.addAttribute("addresseeUser", null);
        } else {
            model.addAttribute("addresseeUser", userService.findById(isPage));
        }
    }
}
