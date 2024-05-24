package com.skander.forum.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Post;
import com.skander.forum.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {

	List<Rate> findByPost(Post p);

}
