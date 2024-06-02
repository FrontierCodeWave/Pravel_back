package com.tour.prevel.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
@Getter
public class User {

    @Id
    private String email;
    private String password;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
