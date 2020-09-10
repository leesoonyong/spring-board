package com.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.PageMaker;
import com.board.domain.Paging;
import com.board.domain.ReplyVO;
import com.board.domain.Search;
import com.board.service.BoardService;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	 @Autowired
	 private BoardService service;
	 
	 @Autowired
	 private ReplyService repService;
	 
	 
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 public void getList(Model model) throws Exception {
		 List<BoardVO> list = service.list();
		 model.addAttribute("list",list);
	   
	 }
	 
	 @RequestMapping(value = "/write", method = RequestMethod.GET)
	 public void getWrite(HttpSession session, Model model) throws Exception {
		 Object loginInfo = session.getAttribute("member");
		 
		 if(loginInfo == null) {
			 model.addAttribute("msg", false);
		 }
	 }
	 
	 @RequestMapping(value = "/write", method = RequestMethod.POST)
	 public String postWrite(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
	   service.write(vo, mpRequest);
	   return "redirect:/board/listSearch";
	 }
	 
	 @RequestMapping(value = "/view", method = RequestMethod.GET)
	 public void getView(@RequestParam("bno") int bno,
			 		     @ModelAttribute("search") Search search,Model model) throws Exception {
		 BoardVO vo = service.view(bno);
		 model.addAttribute("view",vo);
		 model.addAttribute("search", search);
		 
		 List<ReplyVO> repList = repService.readReply(bno);
		 model.addAttribute("repList",repList);
		 
		 List<Map<String,Object>>fileList = service.selectFileList(bno);
		 
		 model.addAttribute("file",fileList);
		 
	 }
	 
	 @RequestMapping(value="/fileDown")
	 public void fileDown(@RequestParam Map<String, Object>map, HttpServletResponse response) throws Exception {
		 System.out.println(map);
		 Map<String, Object> resultMap = service.selectFileInfo(map);
		 System.out.println(resultMap);
		 String storedFileName = (String)resultMap.get("STORED_FILE_NAME");
		 String originalFileName = (String)resultMap.get("ORG_FILE_NAME");
		 
		 System.out.println(storedFileName);
		 System.out.println(originalFileName);
		 //파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환
		 byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\Users\\soonyong\\Documents\\file"+storedFileName));
		 response.setContentType("application/octet-stream");
		 response.setContentLength(fileByte.length);
		 response.setHeader("content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		 response.getOutputStream().write(fileByte);
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
	 }
	 
	 @RequestMapping(value = "/modify", method = RequestMethod.GET)
	 public void getModify(@RequestParam("bno") int bno,Model model) throws Exception {
		 BoardVO vo = service.view(bno);
		 model.addAttribute("view",vo);
		 
		 List<Map<String,Object>> fileList = service.selectFileList(vo.getBno());
		 model.addAttribute("file", fileList);
	 }
	 
	 @RequestMapping(value = "/modify", method = RequestMethod.POST)
	 public String getModify(BoardVO vo, @ModelAttribute("search") Search search, RedirectAttributes rttr,
			 				 @RequestParam(value="fileNoDel[]")String[] files,
			 				 @RequestParam(value="fileNameDel[]")String[] fileNames,
			 				 MultipartHttpServletRequest mpRequest) throws Exception {
		 service.modify(vo, files, fileNames, mpRequest);
		 /*if(files == null) {
			 System.out.println("이거 널입니다.");
		 }
		 if(fileNames == null) {
			 System.out.println("이거 널입니다.");
		 }
		 */
		 rttr.addAttribute("bno", vo.getBno());
		 rttr.addAttribute("page", search.getPage());
		 rttr.addAttribute("perPageNum", search.getPerPageNum());
		 rttr.addAttribute("searchType", search.getSearchType());
		 rttr.addAttribute("keyword", search.getKeyword());
		 
		 //return "redirect:/board/view?bno=" + vo.getBno();
		 return "redirect:/board/view";
	 }
	 
	 @RequestMapping(value = "/delete", method = RequestMethod.GET)
	 public String getDelete(@RequestParam("bno") int bno,
			  				 @ModelAttribute("search") Search search,RedirectAttributes rttr) throws Exception {
		 service.delete(bno);
		 
		 rttr.addAttribute("page", search.getPage());
		 rttr.addAttribute("perPageNum", search.getPerPageNum());
		 rttr.addAttribute("searchType", search.getSearchType());
		 rttr.addAttribute("keyword", search.getKeyword());
		 
		 return "redirect:/board/listSearch";
	 }
	 
	 //게시물 목록 + 페이징 추가
	 @RequestMapping(value = "/listPage", method = RequestMethod.GET)
	 public void getListPage(Model model, Paging pg) throws Exception {
		 
		 List<BoardVO> list = service.listPage(pg);
		 model.addAttribute("list",list);
		 
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setPg(pg);
		 pageMaker.setTotalCount(service.listCount());
		 model.addAttribute("pageMaker", pageMaker);
	 }
	 
	 //검색
	 @RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	 public void getListPage(@ModelAttribute("search") Search search, Model model) throws Exception {
		 
		 List<BoardVO> list = service.listSearch(search);
		 model.addAttribute("list",list);
		 
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setPg(search);
		 pageMaker.setTotalCount(service.countSearch(search));
		 model.addAttribute("pageMaker", pageMaker);
	 }
	 
	 //댓글 작성
	 @RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	 public String getListPage(ReplyVO vo, Search search, RedirectAttributes rttr) throws Exception {
		 repService.writeReply(vo);
		 
		 System.out.println("댓글");
		 rttr.addAttribute("bno", vo.getBno());
		 rttr.addAttribute("page", search.getPage());
		 rttr.addAttribute("perPageNum", search.getPerPageNum());
		 rttr.addAttribute("searchType", search.getSearchType());
		 rttr.addAttribute("keyword", search.getKeyword());
		 
		 return "redirect:/board/view";
	 }
	 
	 //댓글 수정
	 @RequestMapping(value = "/replyModify", method = RequestMethod.POST)
	 public String replyModify(ReplyVO vo, Search search, RedirectAttributes rttr) throws Exception {
		 repService.replyModify(vo);
		 
		 rttr.addAttribute("bno", vo.getBno());
		 rttr.addAttribute("page", search.getPage());
		 rttr.addAttribute("perPageNum", search.getPerPageNum());
		 rttr.addAttribute("searchType", search.getSearchType());
		 rttr.addAttribute("keyword", search.getKeyword());
		 
		 return "redirect:/board/view";
	 }
	 
	 //댓글 삭제
	 @RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	 public String replyDelete(ReplyVO vo, Search search, RedirectAttributes rttr) throws Exception {
		 repService.replyDelete(vo);
		 
		 rttr.addAttribute("bno", vo.getBno());
		 rttr.addAttribute("page", search.getPage());
		 rttr.addAttribute("perPageNum", search.getPerPageNum());
		 rttr.addAttribute("searchType", search.getSearchType());
		 rttr.addAttribute("keyword", search.getKeyword());
		 
		 return "redirect:/board/view";
	 }
	 
 	//댓글 수정 GET
	 @RequestMapping(value = "/replyModify", method = RequestMethod.GET)
	 public void getReplyModify(@RequestParam("rno") int rno, 
			 					  @ModelAttribute("search") Search search, Model model) throws Exception {
		 ReplyVO vo = repService.readReplySelect(rno);
		 
		 model.addAttribute("readReply",vo);
		 model.addAttribute("search",search);
		 		 
	 }
	 
	 //댓글 삭제 GET
	 @RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	 public void getReplyDelete(@RequestParam("rno") int rno, 
			 					@ModelAttribute("search") Search search, Model model) throws Exception {
		
		 ReplyVO vo = repService.readReplySelect(rno);
		 
		 model.addAttribute("readReply", vo);
		 model.addAttribute("search", search);
		 
	 }
 
	

}