package com.skander.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Post;
import com.skander.forum.model.Rate;
import com.skander.forum.repos.PostRepository;
import com.skander.forum.repos.RateRepository;


@Service
public class RateServiceImp implements RateService{

	@Autowired
	RateRepository rateRepository;
	@Autowired
	PostRepository postRep;
	@Override
	public Rate saveRate(Rate r) {
		
		Post p=postRep.getById(r.getPost().getIdPost());
		Rate rate=rateRepository.save(r);
		p.setTotalStars((float)Math.floor((double)Statistiques(p)));
		postRep.save(p);
		return rate;
	}

	@Override
	public Rate updateRate(Rate r) {
		return rateRepository.save(r);
	}

	@Override
	public void deleteRate(Rate r) {
		rateRepository.delete(r);
		
	}

	@Override
	public void deleteRateById(Long id) {
		rateRepository.deleteById(id);
		
	}

	@Override
	public List<Rate> getRate(Post p) {
		return rateRepository.findByPost(p);
	}

	@Override
	public List<Rate> getAllRates() {
		
		return rateRepository.findAll();
	}
	
	@Override
	public float Statistiques(Post p)
	{
		List<Rate> rates=rateRepository.findByPost(p);
		float s = 0;
		float stars=0;
		for (int i = 0; i < rates.size(); i++){
			s=s+rates.get(i).getStars();	
		}
		if(rates.size()>0)
		{
			stars=s/(rates.size());
		}
		return stars;
	}
}
