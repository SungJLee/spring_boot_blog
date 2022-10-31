package com.lsj.blog;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 하기 어노테이션 사용하려면 Javax Persistence API Gradle 추가 필요 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(nullable = false, length = 30) // null 방지 및 길이 30
	private String title;
	
	@Lob // 대용량 데이터일시 사용 
	private String content;
	
	@ColumnDefault("0") // int 형이라서 ''안에 들어갈 필요가 없다. 
	private int count;
	
	
	// @ManyToOne(fetch = FetchType.EAGER) // LAZY 전략 : 있으면 가져오고 없으면 가져오지 말기   
	@ManyToOne(fetch = FetchType.EAGER) // EAGER 전략 : 무조건 조인해서 가져와라 
	// 다대일 관계 - Many : Board, One : BlogUser 한명이 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId") // 필드명은 userId가 되고 FK키가 된다.
	private BlogUser user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	// JPA에서는 이러한 관계를 알아서 처리를 해준다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  
	// select를 통해서 값을 얻기 위해 필요한 것 (DB컬럼이 Create로 생성되어서는 안 된다)
	private List<Reply> reply; 
	
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
