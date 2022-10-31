package com.lsj.blog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @Getter // Getter 생성
// @Setter // Setter 생성 
// @RequiredArgsConstructor // final 필드가 존재할 경우 생성자
@Data // Getter + Setter 생성
// @AllArgsConstructor // 생성자 생성
@NoArgsConstructor // 기본생성자
public class Account {

	// private final String id; // DB에서 값을 받아와 수정할 일이 없을 때는 final 로 쓰는게 trend
	// private final int password; // DB에서 값을 받아와 수정할 일이 없을 때는 final 로 쓰는게 trend 
	private String id;
	private int password;
	
	@Builder // 내가 원하는 생성자 파라미터 지정 가능 (자세한건 TestController에서 봐주세요)
	public Account(String id, int password) {
		this.id = id;
		this.password = password;
	}
	
	
	
}
