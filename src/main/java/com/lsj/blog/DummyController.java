package com.lsj.blog;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsj.blog.model.RoleType;
import com.lsj.blog.repository.UserRepository;

@RestController
public class DummyController {

	@Autowired // DI
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(BlogUser user) { 
		
		//user.setRole("user"); // @DynamicInsert처럼 계속 어노테이션을 붙히다보면 어노테이션 지옥에 빠지기 때문에 직접 Setter에 주입해 어노테이션을 줄일 수 있지만 실수할 수 있다.
		user.setRole(RoleType.USER); // Enum을 이용한 방식을 통해 Setter 주입 실수를 방지할 수 있다. 
		userRepository.save(user); // UserRepostiory에 save하면 DB 테이블에 insert가 된다.
		
		return "회원가입이 완료되었습니다.";
	}
	
	@GetMapping("/dummy/user/{id}")
	public BlogUser detail(@PathVariable int id) { // PathVariable은 들어온 id값에 따른 동적 처리 {id}
		
		
		// BlogUser user = userRepository.findById(id); // findById의 return 타입은 Optional
													    // → 자바에서 DB 데이터를 못찾아오게 되면 user가 null이 되어 return이 되기 때문에
  													    // Optional로 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해라
		/** 방법1 **/
		// userRepository.findById(id).get(); // null 그런거 상관없이 가져오겠다. (위험) 
		
		/**  방법 2
		BlogUser user = userRepository.findById(id).orElseGet(new Supplier<BlogUser>(){
			@Override
			public BlogUser get() { // 값이 없는 경우 BlogUser 객체 생성해 주입 (Null 가능성은 없음)
				return new BlogUser();
			}
		});
		**/
		
		/** 방법3 (선호) 해당 에러 발생시 Throw **/
		BlogUser user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		return user;
	}
	
	// 페이지리스트 모두 출력
	@GetMapping("/dummy/list")
	public List<BlogUser> list(){
		return userRepository.findAll();
	}
	
	// 페이지네이션
	@GetMapping("dumpy/list/page")
	public Page<BlogUser> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<BlogUser> users = userRepository.findAll(pageable);
		return users;
	}
	
}