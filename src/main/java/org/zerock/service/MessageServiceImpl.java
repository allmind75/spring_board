package org.zerock.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	//@Transactional에 의해 messageDAO.create와 pointDAO.updatePoint() 가 둘다 성공해야 commit됨
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		//메시지를 남긴사람은 10pt
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}
	
	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		//메시지를 읽은 사람은 5pt
		messageDAO.updateState(mno);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.readMessage(mno);
	}
	
}
