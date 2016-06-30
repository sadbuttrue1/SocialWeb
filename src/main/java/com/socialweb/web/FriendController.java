/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.web;

import com.socialweb.security.User;
import com.socialweb.service.FriendService;
import com.socialweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author alexandr
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String addFriend(@PathVariable("userId") int userId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();        
        friendService.addFriend(userId, principal.getId());
        return "good";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allFriend(Model model) {        
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //UserData user = userService.findById(principal.getId());        
        model.addAttribute("userFriendsConfirm", friendService.friendsConfirmList(principal.getId()));
        model.addAttribute("userFriends", friendService.friendsList(principal.getId()));
        model.addAttribute("myFriends", true);
        return "allFriend";
    }
    
    @RequestMapping(value = "/all/{userId}", method = RequestMethod.GET)
    public String allFriend(Model model, @PathVariable("userId") int id) {                
        //UserData user = userService.findById(id);                
        model.addAttribute("userFriends", friendService.friendsList(id));
        model.addAttribute("myFriends", false);
        return "allFriend";
    }
    
    @RequestMapping(value = "/confirm/{friendId}", method = RequestMethod.GET)
    public String confirmFriend(Model model, @PathVariable("friendId") int friendId){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendService.friendConfirm(principal.getId(), friendId);
        return "redirect:/friend/all";
    }
    
    @RequestMapping(value = "/del/{friendId}", method = RequestMethod.GET)
    public String delFriend(Model model, @PathVariable("friendId") int friendId){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendService.friendDel(principal.getId(), friendId);
        return "redirect:/friend/all";
    }
}
