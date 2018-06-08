package com.collaboration.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.UserDao;
import com.collaboration.domain.User;

@RestController

public class UserController {

	@Autowired
	private User user;
	@Autowired
	private UserDao userDao;
	


	// http://localhost:8080/CollRestController/
	@GetMapping("/")
	public String serverTest() {
		return "This is my first webservice";
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<String> registerUser(@RequestBody User user)
	{
		
		
		if(userDao.registerUser(user)==true)
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Fail",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping("/user/checkcredentials")
    public ResponseEntity<User> checkCredentials(@RequestBody User user,HttpSession session)
    {
		boolean a=userDao.checkCredential(user.getLoginName(), user.getPassword());
		if(a==true)
		{
			User tempUser=userDao.getUser(user.getLoginName());
           session.setAttribute("user",tempUser);	
           return new ResponseEntity<User>(tempUser,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
		
    }
	
	@GetMapping("/user/getUser/{loginName}")
	public ResponseEntity<User> getUser(@PathVariable String loginName)
	{
		
		user=userDao.getUser(loginName);
	    if(user!=null)
	    {
	    	return new ResponseEntity<User>(user,HttpStatus.OK);
	    }
	    user=new User();
		return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
}
