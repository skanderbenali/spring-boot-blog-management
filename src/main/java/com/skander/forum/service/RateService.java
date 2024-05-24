package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Post;
import com.skander.forum.model.Rate;


public interface RateService {

	Rate saveRate(Rate r);
	Rate updateRate(Rate r);
	void deleteRate(Rate r);
	void deleteRateById(Long id);
	List<Rate> getRate(Post p);
	List<Rate> getAllRates();
	float Statistiques(Post p);
}
