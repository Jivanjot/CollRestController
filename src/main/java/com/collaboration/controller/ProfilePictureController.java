package com.collaboration.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.collaboration.dao.ProfilePictureDao;
import com.collaboration.domain.ProfilePicture;
import com.collaboration.domain.User;

@RestController
public class ProfilePictureController {

	@Autowired
	private User user;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ProfilePicture profilePicture;
	
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	@PostMapping("/uploadImage")
	public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") CommonsMultipartFile fileUpload)
	{
		User user=(User) session.getAttribute("user");
		if(user.getLoginName()==null)
		{
			return new ResponseEntity<String>("Unauthorize user",HttpStatus.NOT_FOUND);
		}
		//ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setLoginName(user.getLoginName());
		profilePicture.setImage(fileUpload.getBytes());
		profilePictureDao.save(profilePicture);
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
	}
	
   @GetMapping("getPicture/{loginName}")
   
   public byte[]  getProfilePicture(@PathVariable String loginName)
   {
	  
	   ProfilePicture profilePicture= profilePictureDao.getProfilePicture(loginName);
	   
		   if(profilePicture.getImage()!=null)
		   {
			   return profilePicture.getImage();
	   }
		   else
		   {
			   return null;
		   }
   }




}


