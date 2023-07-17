package com.stateful.spring.security.service;

import com.stateful.spring.security.config.MyUserDetails;
import com.stateful.spring.security.entity.UserEntity;
import com.stateful.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.getUserByUsername(username);
        UserDetails userDetails = new MyUserDetails(userEntity);

        if (userDetails == null){
            throw new UsernameNotFoundException("User does not exists with username " + username);
        }

        return userDetails;
    }
}
