package com.cpan282.clotheswarehouse.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpan282.clotheswarehouse.model.User;

// It act as the heart for the user operations.
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
