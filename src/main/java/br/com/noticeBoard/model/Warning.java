package br.com.noticeBoard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_warnings")
public class Warning {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "The attribute title is mandatory!!")
	@Size(min = 1, max = 50, message = "The attribute title must have between  01 -> 50 characters")
	private String title;
	
	@NotNull(message = "The attribute title is mandatory!!")
	@Size(min = 10, max = 500, message = "The attribute description must have between  10 -> 500 characters")
	private String description;
	
	
	//Data de publicacao
	@CreationTimestamp
	@Column(name = "dt_post", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate = new java.sql.Date(System.currentTimeMillis());
	
	
	//Data de visualizacao da notificacao
	@CreationTimestamp
	@Column(name = "dt_viewed", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date viewedDate = new java.sql.Date(System.currentTimeMillis());


	//--------------------GETTERS/SETTERS---------------------------------
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getPostDate() {
		return postDate;
	}


	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	public Date getViewedDate() {
		return viewedDate;
	}


	public void setViewedDate(Date viewedDate) {
		this.viewedDate = viewedDate;
	}
}
