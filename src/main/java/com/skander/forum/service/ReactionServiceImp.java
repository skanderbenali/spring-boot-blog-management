package com.skander.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Reaction;
import com.skander.forum.repos.ReactionRepository;

@Service
public class ReactionServiceImp implements ReactionService {

	@Autowired
	ReactionRepository ReactionRepository;
	
	@Override
	public Reaction saveReaction(Reaction r) {
		return ReactionRepository.save(r);
	}

	@Override
	public Reaction updateReaction(Reaction r) {
		return ReactionRepository.save(r);
	}

	@Override
	public void deleteReaction(Reaction r) {
		ReactionRepository.delete(r);
		
	}

	@Override
	public void deleteReactionById(Long id) {
		ReactionRepository.deleteById(id);
		
	}

	@Override
	public Reaction getReaction(Long id) {
		return ReactionRepository.findById(id).get();
	}

	@Override
	public List<Reaction> getAllReactions() {
		
		return ReactionRepository.findAll();
	}
}
