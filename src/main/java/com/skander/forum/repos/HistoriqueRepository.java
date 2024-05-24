package com.skander.forum.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Historique;
import com.skander.forum.model.User;


public interface HistoriqueRepository extends JpaRepository<Historique, Long> {

	List<Historique> getByUser(User u);
	List<Historique> findByUser(User u);

}
