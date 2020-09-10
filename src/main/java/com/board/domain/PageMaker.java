package com.board.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Paging pg;
	
	public void setPg(Paging pg) {
		this.pg = pg;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pgData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	
	public Paging getPg() {
		return pg;
	}
	
	private void pgData() {
		endPage =  (int) (Math.ceil(pg.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)pg.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		 prev = startPage == 1 ? false : true;
		  next = endPage * pg.getPerPageNum() >= totalCount ? false : true;
		
	}
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", pg.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", pg.getPerPageNum())
				.queryParam("searchType", ((Search)pg).getSearchType())
				.queryParam("keyword", encoding(((Search)pg).getKeyword()))
	            .build(); 
		return uriComponents.toUriString();
	}
	
	private String encoding(String keyword) {
		if(keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			return "";
		}
	}
}
