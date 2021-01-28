package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Table 化 시키는 annotation
//User class 읽어서 자동으로 mysql 테이블 생성 
//ORM → JAVA다른언어 포함  Object 를 테이블로 매핑

@Data //getter setter
@NoArgsConstructor //빈생성자
@AllArgsConstructor //전체생성자
@Builder //빌더패턴 
@Entity
//@DynamicInsert (insert 시에 null  인 필드를 제외시켜준다 
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;//seq, auto_increment
	
	@Column(nullable = false,length = 30)
	//null이 되면 안된다
	private String username;//ID
	
	@Column(nullable = false,length = 100) // why 길이가 100 ? → 비밀번호 해쉬(암호화)
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;//Enum을 쓰는게 좋다 why ? data의 domain(범위)을 만들어 줄 수 있다. ex유저의 권한설정? - Code값으로 관리하는게 편하다
	// 그러므로 제한된 문자열로 설정 해 두는것이 좋다.
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	
}
