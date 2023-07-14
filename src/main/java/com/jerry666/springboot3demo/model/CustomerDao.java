package com.jerry666.springboot3demo.model;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

@Repository
public class CustomerDao {
	
	@PersistenceContext
	private Session session;
	
	//hibernate原生寫法
	public Customer getById(Integer id) {
		Customer result = session.get(Customer.class, id);
		return result;
	}
}
