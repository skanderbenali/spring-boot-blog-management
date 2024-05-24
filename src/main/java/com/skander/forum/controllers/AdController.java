package com.skander.forum.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skander.forum.model.Ad;
import com.skander.forum.model.PopulationCible;
import com.skander.forum.model.User;
import com.skander.forum.repos.PopulationCibleRepository;
import com.skander.forum.repos.UserRepository;
import com.skander.forum.service.AdService;

@RequestMapping("/Ad")
@Controller
public class AdController {
	private String question;
	private String type;
	private Date dateDebut;
	private Date dateFin;
	private float cout;
	private String gender;
	private int minAge=0;
	private int maxAge=0;
	private String location;
	private String link;
	@Autowired
	private AdService adService;
	@Autowired
	private PopulationCibleRepository popciblerepos;
	@Autowired
	private UserRepository userRepos;
	
	
	@PostMapping(value="/chatbott")
	@ResponseBody
	public String Chatbot(@RequestParam("reponse") String reponse) throws ParseException
	{
		Ad ad= new Ad();
		User currentuser = userRepos.findById((long)1).orElse(null);
		ad.setUser(currentuser);
		if(reponse.equals("Hi Bot"))
		{
			question="Hello, how can I help you ?\nCreate Ad\nUpdate Ad\nCancel Ad\nShow Ads\nShow Your Ads";
			return question;
		}
		if(question.equals("Hello, how can I help you ?\nCreate Ad\nUpdate Ad\nCancel Ad\nShow Ads\nShow Your Ads"))
		{
			if(reponse.equals("Create Ad"))
			{
				question="what is your ad type ? (examples: comercial/mediatization/monetisation..)";
				return question;
			}
			if(reponse.equals("Show Ads"))
			{	String delim = "-";
				StringBuilder sb = new StringBuilder();
				List<Ad> ads=adService.getAll();
				 int i = 0;
			        while (i < ads.size() - 1)
			        {
			            sb.append(ads.get(i).toString());
			            sb.append(delim);
			            i++;
			        }
			        sb.append(ads.get(i));
			        question="";
			       String res = sb.toString();
			       return res +"\n---------------------------------\nto Reboot ChatBot write Hi Bot";
			}
			if(reponse.equals("Show Your Ads"))
			{	User u=new User((long)1); //replace with current logged in user
				String delim = "-";
				StringBuilder sb = new StringBuilder();
				List<Ad> ads=adService.getByUser(u);
				 int i = 0;
			        while (i < ads.size() - 1)
			        {
			            sb.append(ads.get(i).toString());
			            sb.append(delim);
			            i++;
			        }
			        sb.append(ads.get(i));
			        question="";
			       String res = sb.toString();
			       return res +"\n---------------------------------\nto Reboot ChatBot write Hi Bot";
			}
			if(reponse.equals("Cancel Ad"))
			{
				question="write the ad id to cancel it";
				return question;
			}
		}
		if(question.equals("write the ad id to cancel it"))
		{
			Long id = Long.parseLong(reponse);
			adService.deleteById(id);
			question="";
			return "your ad has been successfully deleted , if you want to reboot chatbot type Hi Bot";
		}
		
		if(question.equals("what is your ad type ? (examples: comercial/mediatization/monetisation..)"))
		{
			type=reponse;
			question="when your campaign starts ?(format dd/MM/yyyy)";
			return question;
		}
		
		if(question.equals("when your campaign starts ?(format dd/MM/yyyy)"))
		{
			dateDebut=new SimpleDateFormat("dd/MM/yyyy").parse(reponse);
			question="when does it end ?";
			return question;
		}
		if(question.equals("when does it end ?"))
		{
			dateFin=new SimpleDateFormat("dd/MM/yyyy").parse(reponse);
			question="how much are you willing to pay ?";
			return question;
		}
		if(question.equals("how much are you willing to pay ?"))
		{
			cout=Float.parseFloat(reponse);
			question="what gender do u want to target ?(male/female/other/all)";
			return question;
		}
		if(question.equals("what gender do u want to target ?(male/female/other/all)"))
		{
			gender=reponse;
			question="whats the minimum age?";
			return question;
		}
		if(question.equals("whats the minimum age?"))
		{
			minAge=Integer.parseInt(reponse);
			question="whats the maximum age?";
		}
		if(question.equals("whats the maximum age?"))
		{
			maxAge=Integer.parseInt(reponse);
			question="Any Specific locations to target ?(location,location,location...)";
			return question;
		}
		if(question.equals("Any Specific locations to target ?(location,location,location...)"))
		{
			location=reponse;
			question="do u want to attach a link to the ad ?(Y/N)";
			return question;
		}
		if(question.equals("do u want to attach a link to the ad ?(Y/N)"))
		{
			if(reponse.equals("Y"))
			{
				question="write your link";
				return question;
			}
			else if(reponse.equals("N"))
			{
				question="what canal do u want to go through?\nfacebook\ntwitter\ngoogleAds";
				return question;
			}
		}
		if(question.equals("write your link"))
		{
			link=reponse;
			ad.setLink(link);
			question="what canal do u want to go through?\nfacebook\ntwitter\ngoogleAds";
			return question;
		}
		if(question.equals("what canal do u want to go through?\nfacebook\ntwitter\ngoogleAds"))
		{
			if(reponse.equals("facebook"))
			{
				ad.setCanal(com.skander.forum.model.canal.facebook);
			}
			else if(reponse.equals("twitter"))
			{
				ad.setCanal(com.skander.forum.model.canal.twitter);
			}
			else if(reponse.equals("googleAds"))
			{
				ad.setCanal(com.skander.forum.model.canal.googleAds);
			}
			PopulationCible popcible=new PopulationCible();
			popcible.setGender(gender);
			popcible.setLocation(location);
			popcible.setMaxAge(maxAge);
			popcible.setMinAge(minAge);
			popciblerepos.save(popcible);
			ad.setCible(popcible);
			ad.setCout(cout);
			ad.setDateDebut(dateDebut);
			ad.setDateFin(dateFin);
			ad.setType(type);
			ad.setLink(link);
			ad.setNbrInitVues(0);
			ad.setNbrCibleVues(adService.Estimated(ad));
			adService.save(ad);
			question="";
			return "Your Ad has been created Successfully! \nTo reboot ChatBot please write (Hi Bot)";
		}
		return "write something ! or say 'Hi Bot' to reboot";
	}
	
	@PutMapping(value="/test")
	public String chatt()
	{
		return "write something ! or say 'Hi Bot' to reboot " ;
	}
	
	
}
