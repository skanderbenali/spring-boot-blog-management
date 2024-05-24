package com.skander.forum.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;

import com.skander.forum.service.CommentService;
import com.skander.forum.service.PostService;
import com.skander.forum.model.Comment;
import com.skander.forum.model.Post;
import com.skander.forum.model.User;


@RestController
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	PostService postservice;
	@RequestMapping("/showCreateCom")
	public String showCreate()
	{
		return "createComment";
	}
	
	@PostMapping(value="/saveComment")
	public String saveComment(@RequestBody Comment comment,@RequestParam Long idPost)
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
		comment.setUser(currentuser);
		Post p = new Post(idPost);
		comment.setPost(p);
        comment.setCreationDate(dateCreation);
        
        commentService.saveComment(comment);
		return "createComment";
	}
	
	@GetMapping("/ListeComments")
	public List<Comment> listeComments()
	{
		List<Comment> Comments = commentService.getAllComments();	
		return Comments;	
	}
	
	@DeleteMapping("/supprimerComment/{id}")
	public String supprimerComment(@PathVariable("id") Long id)
	{
		Comment c= commentService.getComment(id);
		commentService.deleteComment(c);
		return "comment deleted!";	
	}
	
	@GetMapping("/getCommentById")
	public Comment editerComment(@RequestParam Long id)
	{
		Comment c= 	commentService.getComment(id);
		return c;	
	}

	@PutMapping("/updateComment")
	public String updateComment(@RequestBody Comment comment)
	{
		Comment c= 	commentService.getComment(comment.getIdComment());
		comment.setCreationDate(c.getCreationDate());
		comment.setPost(c.getPost());
		comment.setReactions(c.getReactions());
		comment.setUser(c.getUser());
        commentService.updateComment(comment);
		return "comment updated!";
	}
	
	@GetMapping("/getCommentsByPost/{id}")
	public List<Comment> getByPostt(@PathVariable("id") Long id)
	{
		Post p = postservice.findById(id);
		return commentService.getByPost(p);
		
	}
	

}
