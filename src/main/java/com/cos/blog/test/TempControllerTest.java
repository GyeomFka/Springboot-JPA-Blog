package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

//Controller vs restcontroller

//static 이하는 브라우저가 읽을수있는 파일만 들어가야한다 ex)img 파일
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		
		System.out.println("tempHome()");
		
		//파일리턴 기본경로 : src/main/resources/static
		//리턴명 : /homt.html
		return "/home.html";
	}
	
	//static 이하 html도 찾고,ㅏ 이미지도 찾는다 but jsp파일은 못 찾는다 why ? jsp는 동적인 파일이라서 컴파일이 일어나야하는 파일이다!!

	@GetMapping("/temp/jsp")
	public String tempJst() {
		
		//prefix : /WEB-INF/views/
		// suffix : .jsp
		
		// /WEB-INF/views//test.jsp.jsp
		
//		return "/test.jsp";
		return "test";
	}
}
