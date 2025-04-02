package com.petpacket.final_project.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "\"Blog\"", schema = "public")
public class Blog {

	@Id
	@Column(name = "blog_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blogId;

	@Column(name = "title")
	private String title;

	@Column(name = "content", length = 5000)
	private String content;

	@Column(name = "image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "blog")
	private List<Comment> comments;

	@CreationTimestamp
	@Column(name = "create_at")
	private LocalDateTime createdAt;

	@CreationTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
