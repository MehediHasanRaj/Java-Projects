package com.raj.user.service.repositories;

import com.raj.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // if we want to implement any complex query
}
