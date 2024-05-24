package com.skander.forum.controllers;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skander.forum.model.Ban;
import com.skander.forum.model.Historique;
import com.skander.forum.model.Post;
import com.skander.forum.model.User;
import com.skander.forum.repos.BanRepository;
import com.skander.forum.repos.UserRepository;
import com.skander.forum.service.BadWordsFilter;
import com.skander.forum.service.BanService;
import com.skander.forum.service.FileUploadService;
import com.skander.forum.service.HistoriqueService;
import com.skander.forum.service.PostService;
import com.skander.forum.service.RateService;


@RestController
@CrossOrigin(origins = "*")
public class PostController {
	String question="";
	@Autowired
	PostService postService;
	@Autowired
	FileUploadService fileupload;
	@Autowired
	RateService rateservice;
	@Autowired
	BanService banservice;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	UserRepository userRepos;
	@Autowired  
	ServletContext context;
	@RequestMapping("/showCreate")
	public String showCreate()
	{
		return "createPost";
	}
	
	@PostMapping(value="/savePost")
	public String savePost(@RequestParam("description") String description,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException  
	{
		Post post = new Post();
		// Creating the LocalDatetime object
				LocalDate currentLocalDate = LocalDate.now();
				
				// Getting system timezone
				ZoneId systemTimeZone = ZoneId.systemDefault();
				
				// converting LocalDateTime to ZonedDateTime with the system timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
				
				// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
				Date dateCreation = Date.from(zonedDateTime.toInstant());
		User currentuser = userRepos.findById((long)1).orElse(null);
		Ban ban=banservice.getBanByUser(currentuser);
		
		if(ban!=null)
		{
		if(ban.getEndBan().compareTo(dateCreation)<0)
		{
		post.setDescription(description);
		//file upload
		fileupload.uploadfile(file);
		post.setFile(file.getOriginalFilename());
		post.setUser(currentuser);
        post.setDateDeCreation(dateCreation);
		postService.savePost(post);
		return "post created!";
		}
		}
		return "Oops , seems like u are banned from creating a post until " + ban.getEndBan();
	}
	
	
	
	@GetMapping("/ListePosts")
	public List<Post> listePosts()
	{
		List<Post> posts = postService.getAllPosts();	
		return posts;	
	}
	
	@DeleteMapping("/supprimerPost/{id}")
	public String supprimerPost(@PathVariable("id") Long id)
	{
		postService.deletePostById(id);
		return "post deleted!";	
	}
	
	@PostMapping(path="/RatePost/{id}/{rate}")
	@ResponseBody
	public void ratePost(@PathVariable("id") Long id,@PathVariable("rate") float rate)
	{
		Post p = postService.findById(id);
		p.setTotalStars(rate);
		postService.savePost(p);
	}
	
	@GetMapping("/getRatingPost/{id}")
	public float ratingGet(@PathVariable("id") Long id){
		Post p = postService.findById(id);
		return p.getTotalStars();
	}
	
	@GetMapping("/getPostById/{id}")
	public Post editerPost(@PathVariable("id") Long id)
	{
		Post p= postService.getPost(id);
		return p;	
	}

	@PutMapping("/updatePost")
	public String updatePost(@RequestBody Post post) 
	{
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateCreation = Date.from(zonedDateTime.toInstant());
			User currentuser = new User();
			Long id = (long) 1;
			
			currentuser.setIdUser(id);
			post.setUser(currentuser);
			post.setDateDeCreation(dateCreation);
		  postService.updatePost(post);
		 // List<Post> posts = postService.getAllPosts();	
		return "post updated!";
	}
	
	
	@PostMapping(value="/checkPost")
	public String checkPost(@RequestBody Post post)  
	{
		
		// Creating the LocalDatetime object
				LocalDate currentLocalDate = LocalDate.now();
				LocalDate banendlocal = LocalDate.now().plusDays(30);
				// Getting system timezone
				ZoneId systemTimeZone = ZoneId.systemDefault();
				
				// converting LocalDateTime to ZonedDateTime with the system timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
				ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
				// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
				Date dateCreation = Date.from(zonedDateTime.toInstant());
				Date dateEndBan = Date.from(zonedDateTimeEnd.toInstant());
		User currentuser = new User();
		Long id = (long) 1;
		
		currentuser.setIdUser(id);
		post.setUser(currentuser);
        post.setDateDeCreation(dateCreation);
       
        //ban system from here 
        String banmsg="";
        BadWordsFilter.loadConfigs();
       
		String check=BadWordsFilter.filterText(post.getDescription(), "username","post");
        //postService.savePost(post);
		//check ban if needed
		
		//get the ban status of the current user
		Ban b=banservice.getBanByUser(currentuser);
		
		
		if(check!=post.getDescription())
		{
			if(b!=null)
			{
				
					int penalite= b.getPenalites();
					penalite=penalite+1;
					if(penalite>2)
					{
						b.setDebutBan(dateCreation);
						b.setEndBan(dateEndBan);
						b.setPenalites(0);
						b.setStatus(true);
						banmsg="\nSadly you have been banned from creating a post you can no longer add posts until "+dateEndBan;
					}
					else
					{
						b.setPenalites(penalite);
					}
					banservice.updateBan(b);
			}
			else{
				Ban ban= new Ban();
				ban.setPenalites(1);
				ban.setStatus(false);
				ban.setUser(currentuser);
				banservice.saveBan(ban);
			}
		}
		else{
			//save post here
		}
		check=check+banmsg;
        return check;
	}
	
	
	@GetMapping("/PostStatistics")
	public String statistics()
	{
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		LocalDate banendlocal = LocalDate.now().minusDays(30);
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateToday = Date.from(zonedDateTime.toInstant());
		Date dateMounthAgo = Date.from(zonedDateTimeEnd.toInstant());
		
		User u = new User();
		Long idUser=(long) 1;
		u.setIdUser(idUser);
		List<Post> posts  =postService.getPostsByUser(u);
		float stars;
		int totalComments;
		String msg="Your Monthly Review is Here ! \n________________________________________________________\n";
		String starsMsg;
		
		for (int i = 0; i < posts.size(); i++)
		{
			if(posts.get(i).getDateDeCreation().compareTo(dateMounthAgo)>0){
			msg=msg+"Post Id = "+posts.get(i).getIdPost()+"\n"; 
			msg=msg+"Post text : "+posts.get(i).getDescription() +"\n";
			stars=rateservice.Statistiques(posts.get(i));
			totalComments=postService.getPost(posts.get(i).getIdPost()).getComments().size();
			starsMsg=postService.testStars(stars);
			msg=msg+starsMsg+"\nThis Post have "+totalComments+" Comments \n";
			msg=msg+"________________________________________________________\n";
			}
		}
		
		return msg;
	}
	
	@PostMapping("/Search")
	public List<Post> searchPosts (@RequestParam String text)
	{
		User currentUser=new User();
		currentUser.setIdUser((long)1);
		Historique hist = new Historique();
		hist.setUser(currentUser);
		hist.setSearch(text);
		historiqueService.saveHistorique(hist);
		return postService.search(text);
	}
	
	@GetMapping("/recomended")
	public List<Post> recommend()
	{
		User currentUser=new User();
		currentUser.setIdUser((long)1);
		List<Historique> histList = historiqueService.getHistoriqueByUser(currentUser);
		List<Post> recommend = new ArrayList<Post>();
		for (int i = 0; i < histList.size(); i++)
		{
			List<Post> posts = postService.search(histList.get(i).getSearch());
			recommend.addAll(posts);
		}
		Set<Post> mylist = new HashSet<Post>(recommend);
		List<Post> recomm = new ArrayList<Post>(mylist);
		return recomm;
	}
	
	@GetMapping("/getPostsNumber")
	public int getPostsNumber()
	{
		return postService.getAllPosts().size();
	}
	
	
	@GetMapping("/exportPdf")
	public void exportToPDF(HttpServletResponse response){
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filenme=posts.pdf";
		
		response.setHeader(headerKey, headerValue);
		
	}
	
	
	
	@GetMapping("/monthlyreport")
	public int monthlyreport()
	{
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		LocalDate banendlocal = LocalDate.now().minusDays(28);
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateToday = Date.from(zonedDateTime.toInstant());
		Date dateMounthAgo = Date.from(zonedDateTimeEnd.toInstant());
		

		List<Post> posts  =postService.getAllPosts();
		int totalinterracts=0;
		for (int i = 0; i < posts.size(); i++)
		{
			if(posts.get(i).getDateDeCreation().compareTo(dateMounthAgo)>0){
			
				totalinterracts++;
				totalinterracts=totalinterracts+posts.get(i).getRates().size();
				totalinterracts=totalinterracts+posts.get(i).getComments().size();
			}
		}
		
		return totalinterracts;
	}




	
	@GetMapping("/chatBot/{reponse}")
	public String chat(@PathVariable("reponse") String reponse)
	{	
		if(reponse.equals("Hi"))
		{
			question="Hello there ^^ , what do you wish for ? (Post services | Comment Services)";
			return question;
		}
		
		if (question.equals("Hello there ^^ , what do you wish for ? (Post services | Comment Services)" ))
		{
			if(reponse.equals("Post services"))
			{
				question="what do you want us to navigate you to ? (create post | view posts | post statistics)";
				return question;
			}
			else if(reponse.equals("Mental HealthCare"))
			{
				question="do u want to contact an expert?-Psychotherapy-";	
				return question;				
			}
		}
		if(question.equals("what do you want us to navigate you to ? (create post | view posts | post statistics)"))
		{
				return "follow me, if you wan't anything else say Hi again";
		}
		
		return "write something!";
		
	}
	
	@PostMapping("/AddPost")
	@ResponseBody
	public void addProperty(@RequestPart("pub") String c,@RequestPart("file") MultipartFile file) throws JsonParseException ,JsonMappingException , Exception,URISyntaxException
	{	
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		LocalDate banendlocal = LocalDate.now().plusDays(30);
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateCreation = Date.from(zonedDateTime.toInstant());
		Date dateEndBan = Date.from(zonedDateTimeEnd.toInstant());
		//get current user
		User currentuser = userRepos.findById((long)1).orElse(null);
		
		
		Post pub = new ObjectMapper().readValue(c, Post.class);
		
		//System.out.println("Ok ............."+ c);
		
		boolean isExit = new File(context.getRealPath("/Pictures/")).exists();
        if (!isExit)
        {
        	new File (context.getRealPath("/Pictures/")).mkdir();
        	System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Pictures/"+File.separator+newFileName));
        try
        {
        	System.out.println("Image"+ newFileName);
        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        	 
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
        
        
			//save post here
			pub.setFile(newFileName);
	        pub.setUser(currentuser);
	        pub.setDateDeCreation(dateCreation);
	        pub.setTotalStars(0);
			 postService.savePost(pub);
		
        
	}
	
	@GetMapping(path="/ImgPub/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Post pub   = postService.findById(id);
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Pictures/")+pub.getFile()));
	 }
	
	@GetMapping("/getSortedPosts")
	public List<Post> getSorted()
	{
		return postService.getPostsOrderByRate();
	}
	

}
