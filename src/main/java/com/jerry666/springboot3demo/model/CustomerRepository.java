package com.jerry666.springboot3demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query("from Customer where name = ?1")
	public List<Customer> findCustomerByName(String name);
	
	@Query("from Customer where name = :name")
	public List<Customer> findCustomerByName2(@Param("name") String name);
	
	//native sql query
	//呼叫hibernate session內的 createNativeQuery 方法
	@Query(value =  "select * from customer where name = :name",nativeQuery = true)
	public List<Customer> testNativeQuery1(@Param("name") String name);
	
	@Transactional//原先為readonly
	@Modifying //因為Query只能搜尋，所以要寫@Modifying
	@Query("update Customer set name = :name where id =:id")
	public Integer updateNameById(@Param("id") Integer id, @Param("name") String name);
	
	//JPQL snipped
	public List<Customer> findByLevelOrderById(Integer id);
	
	@Query("from Customer where name like %:n%")
	public List<Customer> findByNamelike(@Param("n") String name);
}
