package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.BlogDao;
import com.collaboration.domain.Blog;

@RestController
public class BlogController {

	@Autowired
	private Blog blog;
	@Autowired
	private BlogDao blogDao;

	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		boolean a = blogDao.addBlog(blog);
		if (a) {
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Success", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable int blogId) {
		Blog blog = blogDao.getBlog(blogId);
		if (blog.equals(null)) {
			return new ResponseEntity<Blog>(blog, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);

	}

	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId) {
		boolean a = blogDao.deleteBlog(blogId);
		if (a) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Fail", HttpStatus.BAD_GATEWAY);

	}




   @GetMapping("/listApprovedBlogs")
   public ResponseEntity<List<Blog>> listApprovedBlogs()
   {
	   List<Blog> blogs=blogDao.listApprovedBlogs();   
      if(blogs.size()>0)
      {
    	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
      }
      return new ResponseEntity<List<Blog>>(blogs,HttpStatus.BAD_GATEWAY);
   
   }
   
    

   @GetMapping("/listAllBlogs")
   public ResponseEntity<List<Blog>> listAllBlogs()
   {
	   List<Blog> blogs=blogDao.listAllBlogs();   
      if(blogs.size()>0)
      {
    	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
      }
      return new ResponseEntity<List<Blog>>(blogs,HttpStatus.BAD_GATEWAY);
   
   }

  @GetMapping("/approveBlog/{blogId}")
  public ResponseEntity<String> approveBlog(@PathVariable int blogId)
  {
	boolean a=  blogDao.approveBlog(blogId);
	  if(a)
	  {
		  return new ResponseEntity<String>("success",HttpStatus.OK);
	  }
	  return new ResponseEntity<String>("FAIL",HttpStatus.BAD_GATEWAY);
  }


  @GetMapping("/rejectBlog/{blogId}")
  public ResponseEntity<String> rejectBlog(@PathVariable int blogId)
  {
	boolean a=  blogDao.rejectBlog(blogId);
	  if(a)
	  {
		  return new ResponseEntity<String>("success",HttpStatus.OK);
	  }
	  return new ResponseEntity<String>("FAIL",HttpStatus.BAD_GATEWAY);
  }

  @GetMapping("/incrementLike/{blogId}")
  public ResponseEntity<String> incrementLike(@PathVariable int blogId)
  {
	   if( blogDao.incrementLike(blogId))
	   {
		   return new ResponseEntity<String>("success",HttpStatus.OK);
	   }
		   
	   return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
  }
  
  








}