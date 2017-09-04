package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	/*
	@Test
	public void testCreate() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setTitle("���ο� ���� �ֽ��ϴ�");
		board.setContent("���ο� ���� �ֽ��ϴ�");
		board.setWriter("user00");
		dao.create(board);
	}
	*/
	
	/*
	@Test
	public void testUpdate() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("������ ���Դϴ�.");
		board.setContent("���� �׽�Ʈ");
		dao.update(board);
	}
	*/
	
	/*
	@Test
	public void testDelte() throws Exception {
		dao.delete(2);
	}
	*/
	
	/*
	@Test
	public void testRead() throws Exception {
		BoardVO board = dao.read(1);
		System.out.println(board.toString());
	}
	*/
	
	/*
	@Test
	public void testListPage() throws Exception {
		
		int page = 3;
		
		List<BoardVO> list = dao.listPage(page);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	*/
	
	/*
	@Test
	public void testListCriteria() throws Exception {
		
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		List<BoardVO> list = dao.listCriteria(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}
	*/
	
	/*
	@Test
	public void testURI() throws Exception {
		
		//path�� query�� �ش��ϴ� ���ڿ����� �߰��ؼ� ���ϴ� URI ����
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info(uriComponents.toString());
	}
	*/
	
	@Test
	public void testDynamic1() throws Exception {
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("����");
		cri.setSearchType("t");
		
		logger.info("--------------------------------");
		
		List<BoardVO> list = dao.listSearch(cri);
		
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
		}
		
		logger.info("--------------------------------");

		logger.info("COUNT : " + dao.listSearchCount(cri));
		
		
	}
	
}