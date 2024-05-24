package com.skander.forum.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public void uploadfile(MultipartFile file) throws IllegalStateException, IOException{
		file.transferTo(new File("C:\\Users\\seyko\\Documents\\workspace-sts-3.8.4.RELEASE\\shevoiceapp\\src\\main\\files\\"+file.getOriginalFilename()));
	}
}
