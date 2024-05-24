package com.skander.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Ad;
import com.skander.forum.model.User;
import com.skander.forum.repos.AdRepository;


@Service
public class AdServiceImp implements AdService {

	@Autowired
	AdRepository adRepos;
	
	@Override
	public Ad save(Ad b) {
		return adRepos.save(b);
	}

	@Override
	public Ad update(Ad b) {
		return adRepos.save(b);
	}

	@Override
	public void delete(Ad b) {
		adRepos.delete(b);
	}

	@Override
	public void deleteById(Long id) {
		adRepos.deleteById(id);
	}

	@Override
	public Ad get(Long id) {
		return adRepos.findById(id).orElse(null);
	}

	@Override
	public List<Ad> getByUser(User u) {
		return adRepos.findByUser(u);
	}

	@Override
	public List<Ad> getAll() {
		return adRepos.findAll();
	}
	
	@Override
	public int Estimated(Ad ad)
	{
		float cout=ad.getCout();
		int estimation = (int) (cout/0.01);
		return estimation;
	}

}
