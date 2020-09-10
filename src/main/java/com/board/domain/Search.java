package com.board.domain;

public class Search extends Paging{
	private String searchType ="";
	private String keyword="";
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String ketword) {
		this.keyword = ketword;
	}
	
	@Override
	public String toString() {
		return  super.toString() +"Search [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	
}
