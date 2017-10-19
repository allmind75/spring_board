package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;


//스프링의 빈으로 인식되기 위해 @Service 적용
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {
		
		//게시물 추가
		dao.create(board);
		
		//파일이름 추가
		
		String[] files = board.getFiles();
		
		if(files == null) {return;}
		
		for(String fileName: files) {
			dao.addAttach(fileName);
		}
		
	}
	
	//트랜잭션 격리수준 : 트랜잭션이 완료하기 전에는 데이터에 대한 수정사항을 다른 트랜잭션들이 볼 수 없도록 명시
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}
	
	@Transactional
	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
		
		//수정시 기존 파일 전부 삭제하고, 추가한 파일 등록
		Integer bno = board.getBno();
		
		dao.deleteAttach(bno);
		
		String[] files = board.getFiles();
		
		if(files == null) {return;}
		
		for(String fileName: files) {
			dao.replacetAttach(fileName, bno);
		}
	}
	
	@Transactional
	@Override
	public void remove(Integer bno) throws Exception {
		
		//파일 삭제, tbl_attach가 tbl_board를 참조하기 때문에 반드시 파일을 먼저 삭제
		dao.deleteAttach(bno);
		
		//글 삭제
		dao.delete(bno);	
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}
	
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}
	
	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}
	
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}
	
	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return dao.getAttach(bno);
	}
}
