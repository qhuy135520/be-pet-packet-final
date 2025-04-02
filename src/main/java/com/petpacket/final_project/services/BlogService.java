package com.petpacket.final_project.services;

import com.petpacket.final_project.entities.Blog;
import com.petpacket.final_project.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public List<Blog> getBlogs() {
		return blogRepository.findAll();
	}

	public List<Blog> getThreeBlogs() {
		List<Blog> listBLogs = blogRepository.findAll();
		if (listBLogs.isEmpty() || listBLogs.size() < 3) {
			return null;
		} else {

			return listBLogs.subList(0, 3);
		}
	}

	public Blog getBlogById(Integer id) {
		return blogRepository.findById((id)).orElse(null);
	}

	public Blog saveBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	public List<Blog> getBlogsByUserId(Integer userId) {
		return blogRepository.findByUserUserId(userId); // Assuming you have this method in your BlogRepository
	}

	public void deleteBlogById(Integer id) {
		blogRepository.deleteById(id);
	}
}
