package com.petpacket.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
