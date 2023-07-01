package org.unibl.etf.beans;

import java.util.List;
import org.unibl.etf.dao.MessageDAO;
import org.unibl.etf.dto.Message;

public class MessageBean {
	
	public List<Message> getUserSupports(){
		return MessageDAO.findAll();
	}
	
	public void setMessageAsReviewed(Integer id) {
		MessageDAO.update(new Message(id));
	}
}
