package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.ForumDao;
import com.collaboration.domain.Forum;

@RestController
public class ForumController {

	@Autowired
	ForumDao forumDao;
	@Autowired
	Forum forum;
	

	@GetMapping(value = "/listForum")
	public ResponseEntity<List<Forum>> getForumList() {

		List<Forum> listForums = forumDao.listAllForums();

		if (listForums.size() > 0) {
			return new ResponseEntity<List<Forum>>(listForums, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Forum>>(listForums, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDao.getForum(forumId);

		if (forumDao.deleteForum(forum)) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum) {
		

		if (forumDao.addForum(forum)) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/approveForum/{forumId}")
	public ResponseEntity<String> approveForum(@PathVariable("forumId") int forumId) {
		if (forumDao.approveForum(forumId)) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/rejectForum/{forumId}")
	public ResponseEntity<String> rejectForum(@PathVariable("forumId") int forumId) {
		if (forumDao.rejectForum(forumId)) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/incLikes/{forumId}")
	public ResponseEntity<String> incLikes(@PathVariable int forumId) {
		if (forumDao.incLikes(forumId)) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Fail", HttpStatus.BAD_GATEWAY);

	}

	@GetMapping("/listApprovedForums")
	public ResponseEntity<List<Forum>> listApprovedForums() {
		List<Forum> forums = forumDao.listApprovedForums();
		if (forums.size() > 0) {
			return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
		}

		return new ResponseEntity<List<Forum>>(forums, HttpStatus.BAD_REQUEST);

	}

}
