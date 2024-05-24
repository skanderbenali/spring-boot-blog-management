package com.skander.forum.pdf.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import com.skander.forum.model.Post;

public class PostPdfExporter {
	private List<Post> posts;

	public PostPdfExporter(List<Post> posts) {
		super();
		this.posts = posts;
	}
	
	private void writeTableHeader(){
		
	}
	
	private void writeTableData(){
		
	}
	
	public void export(HttpServletResponse response) {
		//Document document = new Document(PageSize.A4);
	}
	
}
