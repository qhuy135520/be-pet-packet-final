package com.petpacket.final_project.controllers;

import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.Blog;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.services.BlogService;
import com.petpacket.final_project.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getBlogs(){
		List<Blog> listOfBlogs = blogService.getBlogs();
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listOfBlogs);
		successResponse.setMessage("Fetch list of blogs successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/three")
	public ResponseEntity<?> getThreeBlogs(){
		List<Blog> listOfBlogs = blogService.getThreeBlogs();
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listOfBlogs);
		successResponse.setMessage("Fetch list of 3 blogs successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBlogById(@PathVariable  Integer id){
		Blog blog = blogService.getBlogById(id);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(blog);
		successResponse.setMessage("Fetch blog successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> addBlog(@PathVariable Integer id, @RequestBody Blog blog){
		User user = userService.getUserById(id);
		blog.setUser(user);
		Blog savedBlog = blogService.saveBlog(blog);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(savedBlog);
		successResponse.setMessage("Add blog successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getBlogsByUserId(@PathVariable Integer userId) {
		List<Blog> listOfBlogs = blogService.getBlogsByUserId(userId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listOfBlogs);
		successResponse.setMessage("Fetch blogs by userId successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBlogById(@PathVariable Integer id) {
		blogService.deleteBlogById(id);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("Blog deleted successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}


}
