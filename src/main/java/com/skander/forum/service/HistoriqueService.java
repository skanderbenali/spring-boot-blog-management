package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Historique;
import com.skander.forum.model.User;

public interface HistoriqueService {
	Historique saveHistorique(Historique b);
	Historique updateHistorique(Historique b);
	void deleteHistorique(Historique b);
	void deleteHistoriqueById(Long id);
	Historique getHistorique(Long id);
	List<Historique> getHistoriqueByUser(User u);
}
