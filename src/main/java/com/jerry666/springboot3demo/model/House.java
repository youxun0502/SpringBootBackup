package com.jerry666.springboot3demo.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "house")
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "housename")
	private String houseName;
	
	                                                         //預設                   //允許集合裡的物件刪除，若不寫值只能用對方的id進行刪除
	@JsonManagedReference  // 由這邊做 JSON 序列化
	@OneToMany(mappedBy = "house",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<HousePhoto> housePhotos = new ArrayList<>();

}
