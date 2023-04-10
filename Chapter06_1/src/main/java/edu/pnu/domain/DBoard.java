package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String content;
	private Date createDate;
	private Long cnt;
	
	private DBoard(Builder builder) {
		this.seq = builder.seq;
		this.title = builder.title;
		this.content = builder.content;
		this.createDate = builder.createDate;
		this.cnt = builder.cnt;
	}
	
	public DBoard() {
		
	}

	@Override
	public String toString() {
		return "DBoard [seq=" + seq + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", cnt=" + cnt + "]";
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private Long seq;
		private String title;
		private String content;
		private Date createDate;
		private Long cnt;
		
		private Builder() {};
		
		private Builder(Long seq, String title, String content, Date createDate, Long cnt) {
			this.seq = seq;
			this.title = title;
			this.content = content;
			this.createDate = createDate;
			this.cnt = cnt;
		}
		
		public Builder seq(Long seq) {
			this.seq = seq;
			return this;
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		
		public Builder createDate(Date createDate) {
			this.createDate = createDate;
			return this;
		}
		
		public Builder cnt(Long cnt) {
			this.cnt = cnt;
			return this;
		}
		
		public DBoard build() {
			return new DBoard(this);
		}
	}
	
	public Long getSeq() {
			return seq;
		}

		public void setSeq(Long seq) {
			this.seq = seq;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Long getCnt() {
			return cnt;
		}

		public void setCnt(Long cnt) {
			this.cnt = cnt;
		}
}