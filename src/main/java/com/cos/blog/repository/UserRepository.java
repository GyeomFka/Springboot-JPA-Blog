package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO와 같은 역할
//자동으로 BEAN 등록이 된다.

//annotation 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> { //해당 JPA레파지토리는  USER테이블 관리 한다 그 프라이머리 키는 인트다 
	// findall 함수를 갖고있다.
	
	

}
