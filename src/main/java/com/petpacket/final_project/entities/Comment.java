package com.petpacket.final_project.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Comment\"", schema = "public")
public class Comment {

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	@Column(nullable = false, name = "content")
	private String content;

	@Column(name = "create_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createAt;

	@Column(name = "update_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp updateAt;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Comment() {
	}

	public Comment(Integer commentId, String content, Timestamp createAt, Timestamp updateAt, Service service,
			User user) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.service = service;
		this.user = user;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
