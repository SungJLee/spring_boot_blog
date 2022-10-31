package com.lsj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsj.blog.BlogUser;


// extends JapRepository가 있는 경우 @Repository 생략가능
// JpaRepostiory는 다양한 기능을 가지고 있다. findAll (모든 레코드 select) 페이징해서 보내줄 수 있는 기능도 존재
public interface UserRepository extends JpaRepository<BlogUser, Integer>{ // BlogUser의 Repository이고 PK는 Integer이다.
	
} 
//  " " Argos OCR Search 
// 초본 → 이름, 주민번호 (자신이 살아왔던 곳에 거주지) [pdf] 
// uuid 있어보인다.
//  작업자 : 슈퍼관리자 (작업관리자등록 역할)
// 작업자

// DB설계
// pdf 보는 화면
// 대시보드
// 초본 읽어서 데이터 저장후 사행성 판단을 위해 일사편리로 이동후 자동 등록

