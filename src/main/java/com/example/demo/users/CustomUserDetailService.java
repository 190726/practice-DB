package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired ApplicationUsersRepository applicationUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUsers user = applicationUsersRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("No user with" +username+" exists in the system");
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .disabled(!user.getVerified().isState())
                .accountExpired(user.getAccountCredentialExpired().isState())
                .accountLocked(user.getLocked().isState())
                .roles("USER")
                .build();
    }
}
