package com.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberVO;

@Repository("MemberDAO")
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	private static String namespace = "com.board.mappers.member";
	
	
	//회원가입
	public void register(MemberVO vo) {
		mybatis.insert(namespace + ".register", vo);
	}
	
	//로그인
	public MemberVO login(MemberVO vo) {
		return mybatis.selectOne(namespace + ".login", vo);
	}
	//회원정보수정
	public void memberModify(MemberVO vo) {
		mybatis.update(namespace + ".modify", vo);
	}
	//회원탈퇴
	public void memberDelete(MemberVO vo) {
		mybatis.delete(namespace + ".delete", vo);
	}
	//중복체크
	public int idChk(MemberVO vo) {
		int result = mybatis.selectOne(namespace + ".idChk", vo);
		return result;
	}
	//패스워드체크
	public int passChk(MemberVO vo) {
		int result = mybatis.selectOne(namespace + ".passChk", vo);
			return result;
	}
}
