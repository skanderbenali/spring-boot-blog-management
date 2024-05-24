package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Ban;
import com.skander.forum.model.User;

public interface BanService {
	Ban saveBan(Ban b);
	Ban updateBan(Ban b);
	void deleteBan(Ban b);
	void deleteBanById(Long id);
	Ban getBan(Long id);
	Ban getBanByUser(User u);
	List<Ban> getAllBans();
}
