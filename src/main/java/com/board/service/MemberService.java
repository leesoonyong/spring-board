package com.board.service;

import com.board.domain.MemberVO;

public interface MemberService {
	public void register(MemberVO vo);
	public MemberVO login(MemberVO vo);
	public void memberModify(MemberVO vo);
	public void memberDelete(MemberVO vo);
	public int idChk(MemberVO vo);
	public int passChk(MemberVO vo);
}
