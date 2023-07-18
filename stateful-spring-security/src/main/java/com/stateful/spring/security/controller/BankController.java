package com.stateful.spring.security.controller;

import com.stateful.spring.security.config.MyUserDetails;
import com.stateful.spring.security.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
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
    @PreAuthorize(value = "hasAuthority('CUSTOMER')")
    public String checkBalance(){
        return "Your balance is 500";
    }

    @GetMapping(value = "/account-info")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String accountInfo(){
        return "Your account information is below";
    }


    @GetMapping(value = "/bank-setting")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String bankSetting(){
        return "Admin can only do these settings";
    }

    @GetMapping("/authenticated/user")
    public String getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Logged in user name :: {}" , authentication.getName()) ;// def
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        UserEntity userEntity = userDetails.getUserEntity();
        return userEntity.getUsername();
    }

}
