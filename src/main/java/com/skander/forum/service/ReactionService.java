package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Reaction;

public interface ReactionService {

	Reaction saveReaction(Reaction r);
	Reaction updateReaction(Reaction r);
	void deleteReaction(Reaction r);
	void deleteReactionById(Long id);
	Reaction getReaction(Long id);
	List<Reaction> getAllReactions();
}
