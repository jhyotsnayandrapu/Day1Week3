package com.Posidex.Day1W3.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Posidex.Day1W3.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByIsDeletedFalse();
}
