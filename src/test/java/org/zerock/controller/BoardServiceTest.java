package org.zerock.controller;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardServiceTest {

	
	@Inject
	private BoardService service;
	
	/*
	@Test
	public void testRegist() throws Exception {
		
		BoardVO board = new BoardVO();
		board.setTitle("���ο� ���� �ֽ��ϴ�");
		board.setContent("���ο� ���� �ֽ��ϴ�");
		board.setWriter("user01");
		service.regist(board);
	}
	*/
}
