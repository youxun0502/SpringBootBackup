package com.jerry666.springboot3demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jerry666.springboot3demo.model.GoodPhoto;
import com.jerry666.springboot3demo.service.GoodPhotoService;

@Controller
public class GoodPhotoController {

	@Autowired
	private GoodPhotoService gpService;

	@GetMapping("/photo/new")
	public String newPhoto() {
		return "photo/uploadPage";
	}

	@PostMapping("/photo/upload")
	public String uploadPhotoAction(@RequestParam("photoName") String name,
			@RequestParam("photoFile") MultipartFile file) {

		try {
			GoodPhoto goodPhoto = new GoodPhoto();
			goodPhoto.setPhotoName(name);
			goodPhoto.setPhoteFile(file.getBytes());

			gpService.insertGoodPhoto(goodPhoto);

			return "redirect:/";
		} catch (IOException e) {

			e.printStackTrace();
			return "redirect:/photo/new";
		}
	}
	
	@GetMapping("/photo/listAllPhoto")
	public String getAllPhoto(Model model) {
		List<GoodPhoto> list = gpService.listGoodPhoto();
		model.addAttribute("photoList", list);
		return "photo/listPhoto";
	}
	
	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id){
		GoodPhoto photo1 = gpService.getPhotoById(id);
		byte[] photeFile = photo1.getPhoteFile();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
										//檔案, header, HttpStatus
		return new ResponseEntity<byte[]>(photeFile, headers, HttpStatus.OK);
	}
	
//	@GetMapping("/downloadImage/{id}")
//	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id){
//		GoodPhoto photo1 = gpService.getPhotoById(id);
//		byte[] photeFile = photo1.getPhoteFile();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_JPEG);
//										//檔案, header, HttpStatus
//		return new ResponseEntity<byte[]>(photeFile, HttpStatus.OK);
//	}
}
