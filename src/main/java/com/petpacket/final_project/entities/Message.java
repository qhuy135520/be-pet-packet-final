package com.petpacket.final_project.entities;

import java.time.LocalDateTime;

import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Message\"")
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
    
    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @Column(name = "status", nullable = false)
    private String status = "UNREAD";

}
