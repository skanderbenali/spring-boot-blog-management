package com.skander.forum.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
