package com.glovoapp.backender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ComponentScan("com.glovoapp.backender.*")
@EnableAutoConfiguration
class API {

    @RequestMapping("/ping")
    @ResponseBody
    String ping() {
        return "pong";
    }

    public static void main(String[] args) {
        SpringApplication.run(API.class);
    }
}
