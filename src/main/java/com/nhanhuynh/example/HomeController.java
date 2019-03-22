package com.nhanhuynh.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
/*    @RequestMapping("/")
    public String index(){
        return "index";
    }
*/
    @RequestMapping({"/","/login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }


/*   @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        return model;
    }

*/

}

