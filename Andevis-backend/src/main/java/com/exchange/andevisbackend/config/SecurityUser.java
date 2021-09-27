package com.exchange.andevisbackend.config;

import com.exchange.andevisbackend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser extends User implements UserDetails {

    private final Set<GrantedAuthority> authorities = new HashSet<>();


    public SecurityUser() {
    }

    public SecurityUser(User user) {

        authorities.add(new SimpleGrantedAuthority("USER"));
        this.setId(user.getId());
        this.setLogin(user.getLogin());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
