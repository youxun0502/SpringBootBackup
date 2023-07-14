package com.jerry666.springboot3demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "house_photo")
@Data
public class HousePhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Lob
	@JsonIgnore
	@Column(name="photo_file")
	private byte[] photoFile;
	
	@JsonBackReference //不由這邊做對面的JSON序列化
	@ManyToOne
	@JoinColumn(name = "house_id")
	private House house;
}
