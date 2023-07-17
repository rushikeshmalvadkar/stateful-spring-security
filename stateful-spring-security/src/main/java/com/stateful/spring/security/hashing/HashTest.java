package com.stateful.spring.security.hashing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Slf4j
public class HashTest {

    public static void main(String[] args) {

        List<String> passwords = List.of("abc123", "def123");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (String password : passwords) {
            String encodedPassword = encoder.encode(password);
            log.info("For password {} encoded password is {}" ,password, encodedPassword);
        }
    }

}
