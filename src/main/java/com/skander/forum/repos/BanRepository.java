package com.skander.forum.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Ban;
import com.skander.forum.model.User;

public interface BanRepository extends JpaRepository<Ban, Long> {

	Ban findByUser(User u);

}
