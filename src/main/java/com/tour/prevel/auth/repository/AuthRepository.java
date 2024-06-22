package com.tour.prevel.auth.repository;

import com.tour.prevel.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, String> {
}
