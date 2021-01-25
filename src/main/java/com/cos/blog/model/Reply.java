package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	private int id;
	
	@Column(nullable = false, length = 200)//대용량 데이터
	private String content;//섬머노트 라이브러리 사용예정 - <html>태그가 섞여서 디자인됨 용량 UP시켜야함
	
	//하나에 게시글에는 여러개의 답변이있다
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	//하나의 유저는 여러개의 답변을 달수있따
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
