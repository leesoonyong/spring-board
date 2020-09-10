package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.domain.Search;

@Repository("BoardDAO")
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	private static String namespace = "com.board.mappers.board";
	
	public List<BoardVO> boardList(){
		return mybatis.selectList(namespace +".list");
	};
	
	public void write(BoardVO vo) {
		mybatis.insert(namespace + ".write",vo);
	};
	
	//파일업로드
	public void insertFile(Map<String, Object> map) {
		mybatis.insert(namespace + ".insertFile", map);
	}
	
	//첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bno){
		return mybatis.selectList(namespace + ".selectFileList", bno); 
	}
	
	//첨부파일 다운로드
	public Map<String, Object>selectFileInfo(Map<String,Object>map){
		return mybatis.selectOne(namespace + ".selectFileInfo", map);
	}
	
	public BoardVO view(int bno) {
		return mybatis.selectOne(namespace + ".view", bno);
	}
	
	public void modify(BoardVO vo) {
		mybatis.update(namespace + ".modify", vo);
		System.out.println("dao에서 bno" + vo.getBno());
		System.out.println("dao에서 title" + vo.getTitle());
		System.out.println("dao에서 writer" + vo.getWriter());
		System.out.println("dao에서 content" + vo.getContent());
	}
	
	public void delete(int bno) {
		mybatis.delete(namespace + ".delete", bno);
	}
	
	//첨부파일 수정
	public void updateFile(Map<String, Object> map) {
		mybatis.update(namespace + ".updateFile", map); 
	}
	
	
	//페이징
	public List<BoardVO> listPage(Paging pg) {
		
		return mybatis.selectList(namespace + ".listPage", pg);
	}
	public int listCount() {
		return mybatis.selectOne(namespace + ".listCount"); 
	}
	
	//검색
	public List<BoardVO> listSearch(Search search){
		return mybatis.selectList(namespace + ".listSearch", search);
	}
	//검색 결과 총 개수
	public int countSearch(Search search) {
		return mybatis.selectOne(namespace + ".countSearch", search);
	}
	
	public void boardHit(int bno) {
		mybatis.update(namespace + ".boardHit", bno);
	}

}
