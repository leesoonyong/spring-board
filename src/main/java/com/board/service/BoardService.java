package com.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.domain.Search;

public interface BoardService {
	public List<BoardVO> list() throws Exception; 
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
	public List<Map<String,Object>> selectFileList(int bno);
	public Map<String, Object> selectFileInfo(Map<String, Object> map);
	public BoardVO view(int bno);
	public void modify(BoardVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)throws Exception;
	public void delete(int bno);
	public List<BoardVO> listPage(Paging pg);
	public int listCount();
	public List<BoardVO> listSearch(Search search);
	public int countSearch(Search search);
}
