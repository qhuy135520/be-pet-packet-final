package com.petpacket.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
