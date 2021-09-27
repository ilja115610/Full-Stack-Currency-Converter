package com.exchange.andevisbackend.repository;

import com.exchange.andevisbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<User,Long> {

    User findByLogin(String username);

}
