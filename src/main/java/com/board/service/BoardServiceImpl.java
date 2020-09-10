package com.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.domain.Paging;
import com.board.domain.Search;
import com.board.util.FileUtils;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	 @Resource(name="fileUtils")
	 private FileUtils fileUtils;
	
	 @Autowired
	 private BoardDAO boardDAO;
	 
	 @Override
	 public List list() throws Exception {
	  return boardDAO.boardList(); 
	  
	 }

	@Override
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
		boardDAO.write(vo);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest);
		int size = list.size();
		for(int i=0; i<size; i++) {
			boardDAO.insertFile(list.get(i));
		}
	}
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO view(int bno) {
			boardDAO.boardHit(bno);
		return boardDAO.view(bno);
	}

	@Override
	public void delete(int bno) {
		boardDAO.delete(bno);
	}


	@Override
	public List<BoardVO> listPage(Paging pg) {
		
		return boardDAO.listPage(pg);
	}

	@Override
	public int listCount() {
		return boardDAO.listCount();
	}

	@Override
	public List<BoardVO> listSearch(Search search) {
		return boardDAO.listSearch(search);
	}

	@Override
	public int countSearch(Search search) {
		return boardDAO.countSearch(search);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int bno) {
		return boardDAO.selectFileList(bno);
	}

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map)  {
		
		return boardDAO.selectFileInfo(map);
	}

	@Override
	public void modify(BoardVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)throws Exception {
		
		boardDAO.modify(vo);
		
		
		
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(vo, files, fileNames, mpRequest);
				
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			System.out.println(list.get(i));
			if(tempMap.get("IS_NEW").equals("Y")) {
				boardDAO.insertFile(tempMap);
			}else {
				boardDAO.updateFile(tempMap);
			}
		}
		
	}

}