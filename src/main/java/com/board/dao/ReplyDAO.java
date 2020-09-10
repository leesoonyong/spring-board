package com.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyVO;

@Repository("ReplyDAO")
public class ReplyDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	private static String namespace = "com.board.mappers.reply";
	
	
	public List<ReplyVO>readReply(int bno){
		return mybatis.selectList(namespace + ".readReply",bno);
	}
	
	public void writeReply(ReplyVO vo) {
		mybatis.insert(namespace + ".writeReply", vo);
	}
	
	public ReplyVO readReplySelect(int rno) {
		return mybatis.selectOne(namespace + ".readReplySelect", rno);
	}
	
	public void replyModify(ReplyVO vo) {
		mybatis.update(namespace + ".modifyReply", vo);
	}
	
	public void replyDelete(ReplyVO vo) {
		mybatis.delete(namespace + ".deleteReply", vo);
	}
}

