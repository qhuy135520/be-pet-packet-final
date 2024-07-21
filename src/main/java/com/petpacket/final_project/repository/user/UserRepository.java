package com.petpacket.final_project.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
