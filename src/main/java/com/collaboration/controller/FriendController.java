package com.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.FriendDao;
import com.collaboration.domain.Friend;
import com.collaboration.domain.User;

@RestController
public class FriendController {

	@Autowired
	FriendDao friendDao;
	@Autowired
	User user;

	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDao.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("success",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showFriendList")
	public ResponseEntity<List<Friend>> showFriendList(HttpSession session)
	{
		String loginName=(String) session.getAttribute("loginName");
		List<Friend> listFriends=friendDao.showFriendList(loginName);
		
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showPendingFriendRequest")
	public ResponseEntity<List<Friend>> showPendingFriendRequest(HttpSession session)
	{
		String loginName=(String) session.getAttribute("loginName");
		List<Friend> pendingFriendRequest=friendDao.showPendingFriendRequest(loginName);
		
		if(pendingFriendRequest.size()>0)
		{
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.OK);
		}
		else
		{
			
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showSuggestedFriend")
	public ResponseEntity<List<User>> showSuggestedFriend(HttpSession session)
	{
		String loginName=(String) session.getAttribute("loginName");
		List<User> showSuggestedFriend=friendDao.showSuggestedFriend(loginName);
		
		if(showSuggestedFriend.size()>0)
		{
			return new ResponseEntity<List<User>>(showSuggestedFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<User>>(showSuggestedFriend,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId") int friendId)
	{
		if(friendDao.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId") int friendId)
	{
		if(friendDao.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
