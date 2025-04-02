package com.petpacket.final_project.repository;

import com.petpacket.final_project.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findByUserUserId(Integer userId);

}
