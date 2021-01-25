package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//사용자 요청 → 응답(HTML) @Controller 
//사용자 요청 → 응답(Data응답) @RestController


@RestController
public class HttpControllerTest {

	//localhost:8000/blog/http/lombok
	
	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1,"ssar","1234","emal");

		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter : " + m.getId());
		
		return "lomboktest 완료";
	}
	
	
	//http://localhost:8080/http/get(select)
	@GetMapping("/http/get")
//	public String getTest( @RequestParam int id, @RequestParam String username ) {
	public String getTest( Member m ) { //local~~~~ : id=1&usernmae=ssar&password=asd 이 내용을 Member 객체에 넣는다

		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter : " + m.getId());

		
		return "GET요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword();
	}
	
	//http://localhost:8080/http/post(insert)
	// 인터넷 브라우저 요청은 포스트가 불가능하다 !! get만 가능하다!!! 405 error
	@PostMapping("/http/post")
	public String postTest( @RequestBody String text) {
		return "post"+text;
	}
	
	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put";
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete";
	}
	
}
