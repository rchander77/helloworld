package com.example.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/")
    public String getHomepage() {
        logger.info("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
