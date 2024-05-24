package com.skander.forum.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
