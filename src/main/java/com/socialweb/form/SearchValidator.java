/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialweb.form;

import java.util.Locale;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author alexandr
 */
@Component
public class SearchValidator implements Validator {
   

    @Override
    public boolean supports(Class<?> type) {
        return SearchForm.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SearchForm searchForm = (SearchForm) o;

        if (searchForm.getAgeFrom() < 6 && searchForm.getAgeFrom() != 0) {
            errors.rejectValue("ageFrom", "ageFrom.less");
        }
        if (searchForm.getAgeTo() > 120) {
            errors.rejectValue("ageTo", "ageTo.more");
        }
        if (searchForm.getAgeFrom() > searchForm.getAgeTo()) {
            errors.rejectValue("ageFrom", "ageFrom.error");
        }

        if (!EmailValidator.getInstance().isValid(searchForm.getEmail()) && !searchForm.getEmail().isEmpty()) {
       
            errors.rejectValue("email", "email.notValid");
        }
    }

}
