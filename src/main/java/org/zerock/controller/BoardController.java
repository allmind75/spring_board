package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		//Model 클래스는 스프링 MVC에서 기본적으로 제공
		//뷰에 원하는 데이터를 전달하는 일종의 컨테이너 역할
		
		logger.info("register get");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception { 
		
		logger.info("register post");
		logger.info(board.toString());
		
		service.regist(board);
		
		//Model : 스프링 MVC에서 제공하는 데이터 전달용 개체
		//kdy, value 값으로 데이터 저장하는 역할
		//addAttribute("이름", 객체) : 객체에 이름 설정, 뷰에서 이름값을 이용해서 객체 처리
		//addAttribute("객체")		 : 자동으로 저장되는 객체의 클래스명을 앞글자를 소문자로 처리
		//model.addAttribute("result", "suecess");
		
		
		//addFlashAttribute : 리다이렉트 시점에 한번만 사용되는 데이터를 전송할 수 있음
		//브라우저로 전송되지만, URI 상에는 보이지 않고 숨겨진 데이터 형태로 전달
		rttr.addFlashAttribute("msg", "success");
		//return "/board/success";
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) throws Exception {
		
		logger.info("show all List");
		
		//model을 이용해서 모든 게시물을 JSP로 전송
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception {
		
		logger.info("show all List");
		
		//model을 이용해서 모든 게시물을 JSP로 전송
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);
		
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value=  "/read", method = RequestMethod.GET) 
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		
		//@RequestParam -> request.getParameter("bno") 역할 수행
		
		//addAttribute에서 이름 없이 데이터 넣으면
		//자동으로 클래스의 이름을 소문자로 시작해서 사용
		// service.read(bno)의 return 타입이 BoardVO이므로 boardVO로 저장
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value=  "/readPage", method = RequestMethod.GET) 
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		
		//@RequestParam -> request.getParameter("bno") 역할 수행
		
		//addAttribute에서 이름 없이 데이터 넣으면
		//자동으로 클래스의 이름을 소문자로 시작해서 사용
		// service.read(bno)의 return 타입이 BoardVO이므로 boardVO로 저장
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		
		logger.info("mod post");
		
		service.modify(board);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("mod post");
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listPage";
	}
	
	
	
}
