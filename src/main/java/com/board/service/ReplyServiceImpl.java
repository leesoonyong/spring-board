package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.ReplyDAO;
import com.board.domain.ReplyVO;

@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO repDAO;
	
	@Override
	public List<ReplyVO> readReply(int bno) {
		return repDAO.readReply(bno);
	}

	@Override
	public void writeReply(ReplyVO vo) {
		repDAO.writeReply(vo);
	}

	@Override
	public ReplyVO readReplySelect(int rno) {
		return repDAO.readReplySelect(rno);
	}

	@Override
	public void replyModify(ReplyVO vo) {
		repDAO.replyModify(vo);
	}

	@Override
	public void replyDelete(ReplyVO vo) {
		repDAO.replyDelete(vo);
	}

}
