package com.jerry666.springboot3demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jerry666.springboot3demo.model.Messages;
import com.jerry666.springboot3demo.model.MessagesRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessagesRepository mRepo;
	
	public void insert(Messages msg) {
		mRepo.save(msg);
	}
	
	public Messages findById(Integer id) {
		Optional<Messages> optional = mRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void deleteById(Integer id) {
		mRepo.deleteById(id);
	}
	
	public List<Messages> findAll(){
		return mRepo.findAll();
	}
	
	@Transactional
	public Messages updateMessageById(Integer id, String newMsg) {
		Optional<Messages> optional = mRepo.findById(id);
	
		if(optional.isPresent()) {
			Messages msg = optional.get();
			msg.setText(newMsg);
			return msg;
		}
		
		System.out.println("no update data");
		
		return null;
	}
	
	public Messages findLastest() {
	return	mRepo.findFirstByOrderByAddedDesc();
	}
	

	public Page<Messages> findByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "added");
		Page<Messages> page = mRepo.findAll(pgb);
		return page;
	}
}
