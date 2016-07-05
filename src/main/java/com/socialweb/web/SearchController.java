

package com.socialweb.web;

import com.socialweb.domain.UserData;
import com.socialweb.form.SearchForm;
import com.socialweb.form.SearchValidator;
import com.socialweb.service.UserService;
import com.socialweb.utility.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/search")
public class SearchController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SearchValidator searchValidator;
    
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, Locale locale){
        
        model.addAttribute("locale", locale.getLanguage());
        model.addAttribute("searchForm", new SearchForm());
        
        return "search";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String searchTry(Model model, SearchForm searchForm,
            BindingResult result, Locale locale){
        searchValidator.validate(searchForm, result);        
        if(result.hasErrors())
        {
            model.addAttribute("locale", locale.getLanguage());
            return "search";
        }
       
        model.addAttribute("locale", locale.getLanguage());
        model.addAttribute("searchForm", searchForm);
        List<UserData> list = userService.findUsers(searchForm);
        
        int size = list.size();        
        int fromIndex = (searchForm.getPageNumber() - 1) * Help.maxElemPage;
        int toIndex = fromIndex + Help.maxElemPage ;
        if(fromIndex < size){
            list = list.subList(fromIndex, toIndex > size ? size : toIndex);
        }else{
            list.clear();
        }
        
        model.addAttribute("sizeListUser", (int)Math.ceil((double)size/Help.maxElemPage));
        model.addAttribute("UserDatas", list);
        model.addAttribute("maxElemPage", Help.maxElemPage);
        
        return "search";
    }
    
    @RequestMapping(value = "/{userDataId}")
    public String viewUserData(Model model, @PathVariable("userDataId") int id){
        model.addAttribute("user", userService.findById(id));
        //у них одинаковый профайл или разный? Функционал различается? edit добовалять ifом
        return "proile";
    }
}
