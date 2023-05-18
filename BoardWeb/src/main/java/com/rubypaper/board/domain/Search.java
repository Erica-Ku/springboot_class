package com.rubypaper.board.domain;

import lombok.Data;

@Data
public class Search {
	private String searchCondition;
	private String searchKeyword;
	
	public Search() {
		
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + "]";
	}
}