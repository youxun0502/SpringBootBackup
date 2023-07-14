package com.jerry666.springboot3demo.controller;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jerry666.springboot3demo.model.House;
import com.jerry666.springboot3demo.model.HousePhoto;
import com.jerry666.springboot3demo.model.HousePhotoRepository;
import com.jerry666.springboot3demo.model.HouseRepository;

@Controller
public class HouseController {

	@Autowired
	private HouseRepository houseRepo;

	@Autowired
	private HousePhotoRepository housePhotoRepo;
	
	@GetMapping("/house/add")
	public String addHouse() {
		return "house/addHouse";
	}

	@GetMapping("/house/list")
	public String listHouse(Model model) {
		List<House> listHouse = houseRepo.findAll();
		model.addAttribute("listHouse", listHouse);

		return "house/listHouse";
	}

	@ResponseBody
	@GetMapping("/house/photoIds")
	public List<Integer> getHouseImageIdByHouseId(@RequestParam("houseId")Integer houseId){
		House house = houseRepo.getReferenceById(houseId);
		List<HousePhoto> listHousePhoto = house.getHousePhotos();
		List<Integer> photoIds = new LinkedList<>();
		for (HousePhoto onePhoto : listHousePhoto) {
		photoIds.add(onePhoto.getId());
		}
		return photoIds;
	}
	
	@GetMapping("/house/image")
	public ResponseEntity<byte[]>getHouseImage(@RequestParam("id") Integer photoId){
		Optional<HousePhoto> optional = housePhotoRepo.findById(photoId);
		
		if(optional.isPresent()) {
			HousePhoto photo = optional.get();
			byte[] photoFile = photo.getPhotoFile();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photoFile);
		}
		
		return null;
	}
	
	@ResponseBody
	@PostMapping("/house/post")
	public String addHousePost(@RequestParam("houseName") String houseName, @RequestParam("file") MultipartFile[] files)
			throws IOException {
		House house = new House();
		house.setHouseName(houseName);

		List<HousePhoto> housePhotoList = new ArrayList<>();

		for (MultipartFile file : files) {
			HousePhoto housePhoto = new HousePhoto();
			byte[] photoByte = file.getBytes();
			housePhoto.setPhotoFile(photoByte);
			housePhoto.setHouse(house);
			housePhotoList.add(housePhoto);
		}

		house.setHousePhotos(housePhotoList);
		houseRepo.save(house);

		return "上傳 OKOK";

	}
}
