package com.ss.SocialistM.restcontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ss.SocialistB.model.UploadFile;
import com.ss.SocialistB.model.User;


@RestController
public class fileUploadController {

	@Autowired
	private com.ss.SocialistB.dao.UploadFileDao uploadFileDao;

	
	@Autowired
	private com.ss.SocialistB.service.UserService userService;

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public void handleFileUpload(HttpServletRequest request, HttpSession session,
			@RequestParam CommonsMultipartFile fileUpload,HttpServletResponse http) throws Exception {
		String loggedInUserId = (String) session.getAttribute("firstName");
		System.out.println("getting friends of: " + loggedInUserId);
		User user = userService.getUserByUserName(loggedInUserId);
		System.out.println("User emailId: " + user.getEmailID());
		if (fileUpload != null) {
			CommonsMultipartFile file = fileUpload;
			System.out.println("Saving File: " + file.getOriginalFilename());
			UploadFile uploadFile = new UploadFile();
			uploadFile.setFileName(file.getOriginalFilename());
			uploadFile.setUserName(user.getFirstName());
			uploadFile.setData(file.getBytes()); 
			System.out.println("File: " +uploadFile.getData());// image
			uploadFileDao.save(uploadFile);
			// Select * from uploadFile where username = 'Mohammed_Ismail'

			byte[] imagefiles = uploadFile.getData(); // image

			try {
				String path = "C:/Users/Sunny/Desktop/GitBash/OnlineSocial/SocialistF/WebContent/resources/img/"
						+ user.getFirstName();
				System.out.println("Path: " + path);
				File files = new File(path);
				FileOutputStream fos = new FileOutputStream(files);
				fos.write(imagefiles);
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		 try {
			    http.sendRedirect("http://localhost:8087/SocialistF/index.html");
			  } catch (IOException ex) {

			  }
		
	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public ResponseEntity<?> getFile(HttpSession session) {
		User user = (User) session.getAttribute("firstName");
		UploadFile uploadFile = uploadFileDao.getFile(user.getFirstName());
		String name = uploadFile.getFileName();
		System.out.println("Name: " + name);
		System.out.println("File: " + uploadFile.getData());
		byte[] imagefiles = uploadFile.getData();
		return new ResponseEntity<byte[]>(imagefiles, HttpStatus.OK);
	}
}
