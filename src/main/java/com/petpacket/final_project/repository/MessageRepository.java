package com.petpacket.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
