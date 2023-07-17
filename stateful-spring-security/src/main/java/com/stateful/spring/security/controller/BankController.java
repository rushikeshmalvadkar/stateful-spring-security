package com.stateful.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class BankController {

    @GetMapping(value = "/home")
    public String home(){
        return "Welcome to XYZ bank";
    }

    // 3cd28f01-15f9-4190-af85-d93a99835c13

    @GetMapping(value = "/about")
    public String about(){
        return "XYZ bank about information";
    }

    @GetMapping(value = "/contact-us")
    public String contactUs(){
        return "XYZ bank contact form";
    }

    @GetMapping(value = "/check-balance")
    public String checkBalance(){
        return "Your balance is 500";
    }

    @GetMapping(value = "/account-info")
    public String accountInfo(){
        return "Your account information is below";
    }


    @GetMapping(value = "/bank-setting")
    public String bankSetting(){
        return "Admin can only do these settings";
    }

}
