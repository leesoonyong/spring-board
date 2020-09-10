package com.board.service;

import java.util.List;

import com.board.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO>readReply(int bno);
	public void writeReply(ReplyVO vo);
	public ReplyVO readReplySelect(int rno);
	public void replyModify(ReplyVO vo);
	public void replyDelete(ReplyVO vo);
}
