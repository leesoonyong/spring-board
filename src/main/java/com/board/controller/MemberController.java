package com.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.MemberVO;
import com.board.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	//회원가입 GET
	@RequestMapping(value="/register", method= RequestMethod.GET)
	public void getRegister(){
		
	}
	
	//회원가입 POST
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String postRegister(MemberVO vo){
		int result = service.idChk(vo);
		try {
			if(result == 1) {
				return "/member/register";
			}else if(result == 0) {
				//암호화 후 재 패스워드 세팅
				String inputPass = vo.getUserPw();
				String pwd = pwdEncoder.encode(inputPass);
				vo.setUserPw(pwd);
				service.register(vo);
			}
			//아이디가 존재하면 다시 회원가입 페이지로 돌아가기
			//존재하지 않으면 register
		}catch(Exception e) {
			throw new RuntimeException();
		}
		
		return "redirect:/";
	}
	
	//로그인
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) {
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		boolean pwdMatch;
		
		if(login != null) {
			pwdMatch = pwdEncoder.matches(vo.getUserPw(), login.getUserPw());
		} else {
			pwdMatch = false;
		}
		
		System.out.println(pwdEncoder.matches(vo.getUserPw(),login.getUserPw()));
		System.out.println(login.getUserPw());
		System.out.println(vo.getUserPw());
		if(login != null && pwdMatch == true) {
		session.setAttribute("member", login);
		} else {
		session.setAttribute("member", null);
		rttr.addFlashAttribute("msg", false);
		}
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public String login(HttpSession session) {
		
		session.invalidate();
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/memberModifyView", method= RequestMethod.GET)
	public String memberModifyView() {
	
		return "member/memberModifyView";
	}
	
	@RequestMapping(value="/memberModify", method= RequestMethod.POST)
	public String registerModify(MemberVO vo, HttpSession session) {
		System.out.println("하이수정");
		service.memberModify(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}
	
	
	
	@RequestMapping(value="/memberDelete", method= RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		/*String sessionPw = member.getUserPw();
		String voPw = vo.getUserPw();
		
		System.out.println(voPw);
		System.out.println(sessionPw);
		
		
		if(!(sessionPw.equals(voPw))){
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}*/
		System.out.println("하이삭제");
		service.memberDelete(vo);
		session.invalidate();
		
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/idChk", method= RequestMethod.POST)
	public int memberDelete(MemberVO vo) {
		int result = service.idChk(vo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/passChk", method= RequestMethod.POST)
	public boolean passChk(MemberVO vo) {
		MemberVO login = service.login(vo);
		boolean pwdChk = pwdEncoder.matches(vo.getUserPw(), login.getUserPw());
		return pwdChk;
	}
	
	
	
}
