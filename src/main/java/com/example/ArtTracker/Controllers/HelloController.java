package com.bootstrapwithspringboot.webapp.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class HelloContoller {
    private String appMode;

    @Autowired
    public HelloContoller(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Ömerrrr");
        model.addAttribute("mode", appMode);

        return "index";
    }
}


//package com.example.ArtTracker.Controllers;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

//@Controller



public class HelloController {
    @RequestMapping(value="")
   // @ResponseBody
    public String index(){
        return "Hello Dan";
    }
}
