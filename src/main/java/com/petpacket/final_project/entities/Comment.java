package com.petpacket.final_project.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"Comment\"", schema = "public")
public class Comment {
	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	@Column(nullable = false, name = "content")
	private String content;

	@CreationTimestamp
	@Column(name = "create_at", nullable = false)
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(name = "update_at", nullable = false)
	private LocalDateTime updateAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
