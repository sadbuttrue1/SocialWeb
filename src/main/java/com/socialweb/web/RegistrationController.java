/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.web;

import com.socialweb.form.RegistrationForm;
import com.socialweb.service.UserService;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alexandr
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping()
    public String registration(Model model, Locale locale) {

        model.addAttribute("locale", locale.getLanguage());
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";

    }

    @RequestMapping(value = "/try")
    public String tryRegistration(Model model, Locale locale,
            @Valid final @ModelAttribute("registrationForm") RegistrationForm registration,
            final BindingResult result) {
        
            if (result.hasErrors()) {
                model.addAttribute("locale", locale.getLanguage());
                return "registration";
            } else {
                if(userService.createNewUser(registration) == -1)
                {
                    model.addAttribute("locale", locale.getLanguage());
                    model.addAttribute("repeatEmail", messageSource.getMessage("label.repeatEmail", null, locale));
                return "registration";
                }else if(userService.createNewUser(registration) == -2)
                {
                    model.addAttribute("locale", locale.getLanguage());
                    model.addAttribute("repeatEmail", messageSource.getMessage("label.errorServer", null, locale));
                return "registration";
                }
                model.addAttribute("registrationOk", messageSource.getMessage("label.registrationOk", null, locale));
                return "login";
            }
        
            
        }
    }


