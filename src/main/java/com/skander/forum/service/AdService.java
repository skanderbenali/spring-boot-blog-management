package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Ad;
import com.skander.forum.model.User;

public interface AdService {
	Ad save(Ad b);
	Ad update(Ad b);
	void delete(Ad b);
	void deleteById(Long id);
	Ad get(Long id);
	List<Ad> getByUser(User u);
	List<Ad> getAll();
	int Estimated(Ad ad);
}
