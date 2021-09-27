package com.exchange.andevisbackend.service;

import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }


    public User saveUser (User user) {

        user = userAccountRepository.save(user);

        return user;

    }
}
