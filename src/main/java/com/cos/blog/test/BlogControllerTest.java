package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는것은 아니고
			//특정 annotation 이 붙어있는 클래스 파일들을 new해서 IoC 스프링 컨테이너에서 관리해준다
public class BlogControllerTest {

//스프링 -> IoC -> 제어의 역전 new() : X -> 스프링이 new 해준다!! *싱글톤 패턴 + 레퍼런스 변수 스프링 관리*
//패키지 이하 모두 스캔 : 필요한 것들을 메모리 로드 IoC 싱글톤
	
	@GetMapping("/test/hello")
	// http://localhost:8080/test/hello
	public String hello() {
		return "<h1>Hello</h1>";
	}
	
	
}
