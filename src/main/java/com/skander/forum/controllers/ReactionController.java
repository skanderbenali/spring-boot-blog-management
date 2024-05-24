package com.skander.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skander.forum.model.Reaction;
import com.skander.forum.service.ReactionService;
import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;


@RestController
public class ReactionController {


	@Autowired
	ReactionService ReactionService;
	
	@RequestMapping("/showCreateReaction")
	public String showCreate()
	{
		return "createReaction";
	}
	
	@RequestMapping("/saveReaction")
	public String saveRate(@ModelAttribute("reaction") Reaction reaction)
	{
		
      
		Reaction saveReaction = ReactionService.saveReaction(reaction);
		 String msg ="Reaction enregistré avec Id "+saveReaction.getIdReaction();
		return "createRate";
	}
	
	@RequestMapping("/ListeReactions")
	public String listeReactions(ModelMap modelMap)
	{
		List<Reaction> Reactions = ReactionService.getAllReactions();
		modelMap.addAttribute("reactions", Reactions);		
		return "listeReactions";	
	}
	
	@RequestMapping("/supprimerReaction")
	public String supprimerReaction(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Reaction r= new Reaction();
		r.setIdReaction(id);
		ReactionService.deleteReaction(r);
		List<Reaction> Reactions = ReactionService.getAllReactions();
		modelMap.addAttribute("reactions", Reactions);	
		return "listeReactions";	
	}
	
	@RequestMapping("/modifierReaction")
	public String editerReaction(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Reaction r= 	ReactionService.getReaction(id);
		modelMap.addAttribute("reaction", r);	
		return "editerReaction";	
	}

	@RequestMapping("/updateReaction")
	public String updateReaction(@ModelAttribute("reaction") Reaction Reaction,
			                    ModelMap modelMap) throws ParseException, java.text.ParseException 
	{
		ReactionService.updateReaction(Reaction);
		  List<Reaction> Reactions = ReactionService.getAllReactions();
		  modelMap.addAttribute("reactions", Reactions);	
		
		return "listeReactions";
	}
}
