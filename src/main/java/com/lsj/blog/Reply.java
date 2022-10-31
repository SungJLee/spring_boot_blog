package com.lsj.blog;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
public class Reply {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column (nullable =false, length = 200)// 대용량 데이터일시 사용 
	private String content;
	
	@ManyToOne // 다대일 관계 - Many : Reply, One : Board 하나의 게시글에는 여러개의 답변이 온다.
	@JoinColumn(name="boardId") 
	private Board board; 
	
	@ManyToOne // 다대일 관계 - Many : Reply, One : BlogUser 하나의 사용자는 여러개의 답변을 달 수 있다. 
	@JoinColumn(name="userId") // select * from board where id = 1 
	private BlogUser user; 
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
}
