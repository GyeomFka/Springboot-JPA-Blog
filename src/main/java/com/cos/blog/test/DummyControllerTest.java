package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {

	@Autowired //controller가 메모리에 뜰때 변수도 같이 뜬다.
	//이것이 의존성 주임 DI
	private UserRepository userRepository;
	
	//detail과 같지 않냐 ? - get vs put annotation 차이
	@Transactional // 더티체킹 !!
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json data 요청 -> java object(Message converter 의 jackson 라비브러리)로 변환해서 받아줘요
		
		System.out.println(id);
		System.out.println(requestUser.getPassword());
		System.out.println(requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		//save함수는 ID를 전달하지 않으면 insert를 해주고 ID를 전달하면 해당 id에 대한 데이터 여부에 따라 update를 해주고 ,id데이터가 없으면 insert를 한다
		return null;
		
	}
	
	@GetMapping("/dummy/users")
	public List<User>list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")//1페이지당 2건에 데이터를 리턴받을예정
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC )Pageable pageable){
		Page<User>users = userRepository.findAll(pageable);
		return users;
	}
	
	//id주소로 파라메터를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//람다식 ?
		User user = userRepository.findById(id).orElseThrow( new Supplier <IllegalArgumentException> () {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id  : " + id);//추후 illegal 페이지 별도 작성
			}
		});
		//USER 객체 = JAVA OBJECT but 요청은 웹 브라우저
		//MessageConverter Jackson라이브러리를 이용해 userojbect를 Json으로자동생성
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join(요청)
	// http 의 body에 name,pw,mail 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {
	public String join(User user) {
		
		System.out.println(user.getUsername() + "obj로 받는다.");
		System.out.println(user.getPassword() + "obj로 받는다.");
		System.out.println(user.getEmail() + "obj로 받는다.");
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "가입성공!";
		
		
	}
	
}
