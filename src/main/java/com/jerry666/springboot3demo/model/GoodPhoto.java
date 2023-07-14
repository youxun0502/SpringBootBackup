package com.jerry666.springboot3demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="goodphoto")
public class GoodPhoto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="photo_name")
	private String photoName;
	
	@Lob//跟他說這個是圖片
	@Column(name = "photo_file")
	private byte[] photeFile;
	
	public GoodPhoto() {	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public byte[] getPhoteFile() {
		return photeFile;
	}

	public void setPhoteFile(byte[] photeFile) {
		this.photeFile = photeFile;
	}
	
}
