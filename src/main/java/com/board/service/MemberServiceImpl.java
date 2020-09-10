package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.domain.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void register(MemberVO vo) {
		memberDAO.register(vo);
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return memberDAO.login(vo);
	}

	@Override
	public void memberModify(MemberVO vo) {
		memberDAO.memberModify(vo);
		
	}

	@Override
	public void memberDelete(MemberVO vo) {
		memberDAO.memberDelete(vo);
	}

	@Override
	public int idChk(MemberVO vo) {
		int result = memberDAO.idChk(vo);
		return result;
	}

	@Override
	public int passChk(MemberVO vo) {
		int result = memberDAO.passChk(vo);
		return result;
	}

}
