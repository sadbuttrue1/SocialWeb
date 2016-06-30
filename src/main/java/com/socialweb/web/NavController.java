package com.socialweb.web;

import com.socialweb.domain.EmailUserData;
import com.socialweb.domain.UserData;
import com.socialweb.utility.Help;
import com.socialweb.utility.RatioFriendsClass;
import com.socialweb.security.User;
import com.socialweb.service.EmailService;
import com.socialweb.service.FriendService;
import com.socialweb.service.UserService;
import com.socialweb.service.FileService;
import java.io.File;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NavController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FriendService friendService;

    @Autowired
    @Qualifier("fileServiceImpl")
    private FileService fileService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin() {
        return "login";
    }

    @RequestMapping(value = "/signin-failure", method = RequestMethod.GET)
    public String signinFailure() {
        return "error403";
    }

    @RequestMapping(value = "/profile")
    public String profile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/profile/" + userService.findById(user.getId()).getProfileLink();
    }

    @RequestMapping(value = "/profile/{profileLink}")
    public String profileLink(Model model, @PathVariable("profileLink") String profileLink) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserData userData = userService.findByProfileLink(profileLink);

        int statusFriends = friendService.friendStatus(principal.getId(), userData.getId());
                 
        model.addAttribute("RatioFriends", new RatioFriendsClass());
        model.addAttribute("statusFriends", statusFriends);
        model.addAttribute("user", userData);

        return "profile";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings(Model model, Locale locale) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.findByIdEager(principal.getId()));
        model.addAttribute("locale", locale.getLanguage());
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String changeSettings(Model model, Locale locale, @Valid final @ModelAttribute("user") UserData userData,
            final BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("locale", locale.getLanguage());
            return "settings";
        } else if (userService.findBySameProfileLink(userData)) {
            model.addAttribute("locale", locale.getLanguage());
            model.addAttribute("statusChange", messageSource.getMessage("label.sameProfileLink", null, locale));
            return "settings";
        } else {
            try {                
                if (!userData.getPhotoImg().getOriginalFilename().isEmpty()) {                   
                   userData.setPhoto(fileService.saveAvatar(userData.getPhotoImg()));
                }               
                emailService.emailProcessingAfterChanging(userData);
                userService.updateAll(userData);
                model.addAttribute("user", userData);
                model.addAttribute("statusChange", messageSource.getMessage("label.statusChangeOk", null, locale));
                return "settings";
            } catch (Exception e) {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                model.addAttribute("user", userService.findById(user.getId()));
                model.addAttribute("statusChange", messageSource.getMessage("label.errorServer", null, locale)
                        + e.getMessage());
                return "settings";
            }
        }

    }

}
