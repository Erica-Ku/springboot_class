package com.rubypaper.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "BoardJPA")
@DynamicInsert
@DynamicUpdate
@Table(name = "BoardJPA")
public class Board1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String writer;
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date createDate;
	@ColumnDefault("0")
	private Long cnt;
}