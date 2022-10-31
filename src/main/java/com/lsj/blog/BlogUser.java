package com.lsj.blog;

import java.sql.Timestamp;
import java.util.List;

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

import com.lsj.blog.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 하기 어노테이션 사용하려면 Javax Persistence API Gradle 추가 필요 **/
@DynamicInsert // null 인 값일 때는 무시하고 Default값으로 넣어준다
//@ColumnDefault("'user'") 
//private String role;  
/** (O) 
insert 
into
    BlogUser
    (createDate, email, password, username, id) 
values
    (?, ?, ?, ?, default)
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 하기 필드 기준으로 DB에  User 테이블을 생성하라는 의미
public class BlogUser {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// → 즉, DB에 따라 AutoIncrement 또는 Sequence가 결정 되어서 알아서 만들어준다. (자동 증가 PK 필요함으로 이와 같은 행동 함) 
	// (MySql에서는 AutoIncrement, Oracle에서는 Sequence 사용)
	private int id;
	
	@Column(nullable = false, length = 30) // null 방지 및 길이 30
	private String username;
	
	@Column(nullable = false, length = 100) 
	private String password;
	
	@Column(nullable = false, length = 50) 
	private String email;
	
	//@ColumnDefault("'user'") // → Column Default 값 String이기 때문에 ' ' 안에 넣어야한다.
	//private String role; // Enum을 
	
	@Enumerated(EnumType.STRING) // DB에는 RoleType이라는 타입이 없기 때문에 String 이라고 선언
	private RoleType role;
	
	@CreationTimestamp // 현재 시각 바로 자동 입력
	private Timestamp createDate;
	
}
