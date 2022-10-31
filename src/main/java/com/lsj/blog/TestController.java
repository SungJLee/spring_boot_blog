package com.lsj.blog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test/get")
	public String hello(Account account) {
		
		account.setId("hello");
		account.setPassword(1234);
		
		System.out.println("id : " + account.getId() + " password :" + account.getPassword() );
		// id : hello password :1234 출력
		
		account = Account.builder().password(0).build(); // password만 포함된 생성자
		System.out.println("id : " + account.getId() + " password :" + account.getPassword() );
		// id : null password :0
		
		Account account2 = Account.builder().password(150).id("halo").build(); // id, password가 포함된 생성자 
		System.out.println("id : " + account2.getId() + " password :" + account2.getPassword() );
		// id : halo password :150
		
		// @Builder는 다양하게 생성자를 오버로딩해서 만들 수 있다. → 
		return "<h1> get </h1>";
		
	}

	@DeleteMapping("/test/delete")
	public String delete() {
		return "<h1> delete </h1>";
	}
	
	@PostMapping("/test/post")  // Body : json → application/json (Text → text/plain로 요청시 에러 발생 [자동매핑이 안 되어서])
	public String post(@RequestBody Account account) { // MessageConverter라는 Class가 자동 Injection을 해준다.
		return "id : " + account.getId() + "\n password : " + account.getPassword(); 
		// {id:1,password:1234} 으로 요청 
	}
	
	@PostMapping("/test/post2") // Body : Text → text/plain 
	public String post2(@RequestBody String text) { // @requestBody body 데이터를 받기 위해 필요
		return text; // 정말로 맛있어요 으로 요청 
	} 
	
	@PutMapping("/test/put")
	public String put() {
		return "<h1> put </h1>";
	}
	
}
