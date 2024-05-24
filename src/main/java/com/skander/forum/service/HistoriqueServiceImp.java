package com.skander.forum.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Historique;
import com.skander.forum.model.User;
import com.skander.forum.repos.HistoriqueRepository;

@Service
public class HistoriqueServiceImp implements HistoriqueService {

	@Autowired
	HistoriqueRepository historiqueRep;
	
	@Override
	public Historique saveHistorique(Historique b)
	{
		return historiqueRep.save(b);
	}
	
	@Override
	public Historique updateHistorique(Historique b)
	{
		return historiqueRep.save(b);
	}
	
	@Override
	public void deleteHistorique(Historique b)
	{
		historiqueRep.delete(b);
	}
	@Override
	public void deleteHistoriqueById(Long id)
	{
		historiqueRep.deleteById(id);
	}
	
	@Override
	public Historique getHistorique(Long id)
	{
		return historiqueRep.getById(id);
	}
	
	@Override
	public List<Historique> getHistoriqueByUser(User u)
	{
		return historiqueRep.findByUser(u);
	}
	
}
