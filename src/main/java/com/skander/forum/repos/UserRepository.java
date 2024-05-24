package com.skander.forum.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
