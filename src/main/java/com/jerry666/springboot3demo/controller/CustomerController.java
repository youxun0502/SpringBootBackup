package com.jerry666.springboot3demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerry666.springboot3demo.model.Customer;
import com.jerry666.springboot3demo.model.CustomerRepository;

@RestController // 回傳Jason格式
public class CustomerController {

	@Autowired
	private CustomerRepository cRepo;
	
	@PostMapping("/customer/insert")
	public Customer insert() {
		Customer c1 = new Customer();
		c1.setName("蔡啊嘎");
		c1.setLevel(2);

		Customer resCus = cRepo.save(c1);

		return resCus;
	}

	@PostMapping("/customer/insert2")
	public Customer insert2(@RequestBody Customer customer) {
		Customer resCus = cRepo.save(customer);
		return resCus;
	}

	@PostMapping("/customer/insert3")
	public List<Customer> insert3(@RequestBody List<Customer> customers) {
		List<Customer> resList = cRepo.saveAll(customers);
		return resList;
	}

	@GetMapping("/customer/{id}")
	public Customer getById(@PathVariable Integer id) {
		Optional<Customer> optional = cRepo.findById(id);

		if (optional.isPresent()) {
			Customer result = optional.get();
			return result;
		}

		Customer cus = new Customer();
		cus.setName("沒有這筆資料");

		return cus;

	}

	@GetMapping("/customers")
	public List<Customer> testFindAll() {
		return cRepo.findAll();
	}

	@DeleteMapping("/customer/delete")
	public String deleteById(@RequestParam Integer id) {
		Optional<Customer> optional = cRepo.findById(id);

		if (optional.isEmpty()) {
			return "沒有這筆資料";
		}

		cRepo.deleteById(id);
		return "刪除成功";
	}

	@Transactional
	@PutMapping("/customer/update")
	public String updateCustomerById(@RequestParam Integer id, @RequestParam String newName) {
		Optional<Customer> optional = cRepo.findById(id);

		if (optional.isPresent()) {
			Customer customer = optional.get();
			customer.setName(newName);
			return "修改OK";
		}
		return "沒有這筆資料";
	}

	@GetMapping("/customer/page/{pageNumber}")
	public List<Customer> findByPage(@PathVariable Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 2, Sort.Direction.ASC, "id" );
		Page<Customer> page = cRepo.findAll(pgb);
		List<Customer> list = page.getContent();
		
		return list;
	}
	
	@GetMapping("/customer/name")
	public List<Customer> testQuery1(@RequestParam("n") String name){
//		return cRepo.findCustomerByName2(name);
		return cRepo.testNativeQuery1(name);
	}
	
	@PutMapping("/customer/update2")
	public Integer testUpdateQuery(@RequestParam Integer id, @RequestParam String name) {
		Integer result = cRepo.updateNameById(id, name);
		return result;
	}
	
	@GetMapping("/customer/level")
	public List<Customer> testNameQuery(@RequestParam Integer level){
		return cRepo.findByLevelOrderById(level);
	}

	@GetMapping("/customer/namelike")
	public List<Customer> testLike(@RequestParam String name){
		return cRepo.findByNamelike(name);
	}
	
}
