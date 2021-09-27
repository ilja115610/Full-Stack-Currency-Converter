package com.exchange.andevisbackend.config;

import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin("http://localhost:4200")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User optional = userRepository.findByLogin(s);

        if(optional == null){
            throw new UsernameNotFoundException("User does not exist");
        }

        return new SecurityUser(optional);
    }
}
