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
	
	//@Transactional�� ���� messageDAO.create�� pointDAO.updatePoint() �� �Ѵ� �����ؾ� commit��
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		//�޽����� �������� 10pt
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}
	
	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		//�޽����� ���� ����� 5pt
		messageDAO.updateState(mno);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.readMessage(mno);
	}
	
}
