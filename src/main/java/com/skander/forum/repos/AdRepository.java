package com.skander.forum.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Ad;
import com.skander.forum.model.User;

public interface AdRepository extends JpaRepository<Ad, Long> {

	List<Ad> findByUser(User u);

}
