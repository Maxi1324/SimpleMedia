package com.MaxiFischer.SocialMediaAPI.model;

import com.MaxiFischer.SocialMediaAPI.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repository.findByNutzername(s);
        user.orElseThrow(() ->  new UsernameNotFoundException("Not found: " + s));
        if (!user.get().getAktiv()) throw new UsernameNotFoundException("Not found: " + s);
        return user.get().toUserDetails();
    }
}
