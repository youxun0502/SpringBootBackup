package com.jerry666.springboot3demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry666.springboot3demo.model.GoodPhoto;
import com.jerry666.springboot3demo.model.GoodPhotoRepository;

@Service
public class GoodPhotoService {

	@Autowired
	private GoodPhotoRepository gpRepo;

	public GoodPhoto insertGoodPhoto(GoodPhoto gp) {
		return gpRepo.save(gp);
	}

	public List<GoodPhoto> listGoodPhoto() {
		return gpRepo.findAll();
	}

	public GoodPhoto getPhotoById(Integer id) {
		Optional<GoodPhoto> optional = gpRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
