package com.jerry666.springboot3demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jerry666.springboot3demo.dto.MessageUpdateDTO;
import com.jerry666.springboot3demo.model.GoodPhoto;
import com.jerry666.springboot3demo.model.Messages;
import com.jerry666.springboot3demo.service.GoodPhotoService;
import com.jerry666.springboot3demo.service.MessageService;

@Controller
public class MessagesController {

	@Autowired
	private MessageService mService;

	@Autowired
	private GoodPhotoService gpService;
	
	@GetMapping("/messages/add")
	public String addMsg(Model model) {
		Messages lastestMsg = mService.findLastest();
		model.addAttribute("lastestMsg", lastestMsg);
		return "messages/addMessagePage";
	}

	@PostMapping("/messages/post")
	public String postMsg(@RequestParam("text") String text, Model model) {
		Messages m1 = new Messages();
		m1.setText(text);
		mService.insert(m1);
		Messages lastestMsg = mService.findLastest();
		model.addAttribute("lastestMsg", lastestMsg);
		return "messages/addMessagePage";
	}

	@GetMapping("/messages/page")
	public String showMessages(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
		Page<Messages> page = mService.findByPage(pageNumber);

		model.addAttribute("page", page);

		return "messages/showMessages";

	}

	@GetMapping("/messages/edit")
	public String editPage(@RequestParam("id") Integer id, Model model) {
		Messages messages = mService.findById(id);
		model.addAttribute("messages", messages);
		return "messages/editMessage";

	}

	@PutMapping("/messages/edit")
	public String editPost(@ModelAttribute(name = "messages") Messages msg) {
		mService.updateMessageById(msg.getId(), msg.getText());
		return "redirect:/messages/page";
	}

	@DeleteMapping("/messages/delete")
	public String deletePost(@RequestParam("id") Integer id, Model model) {
		mService.deleteById(id);	
		Page<Messages> page = mService.findByPage(1);
		model.addAttribute("page", page);

		//return "redirect:/messages/page";//get請求，發送的是delete請求
		return "messages/showMessages";
	}

	@GetMapping("/messages/ajax")
	public String ajaxPractice() {
		return "messages/ajaxMessage";
	}

	@ResponseBody
	@PostMapping("/messages/api/post")
	public Page<Messages> ajaxMessage(@RequestBody Messages messages) {
		mService.insert(messages);
		Page<Messages> page = mService.findByPage(1);
		return page;
	}

	@GetMapping("/messages/mix")
	public String ajaxMix(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {

		Page<Messages> page = mService.findByPage(pageNumber);
		model.addAttribute("page", page);

		return "messages/ajax-mix";
	}

	@ResponseBody
	@PutMapping("/messages/api/updateOne")
	public String ajaxUpdateMessageApi(@RequestBody MessageUpdateDTO msgDTO) {
		Messages updateMsgObj = mService.updateMessageById(msgDTO.getId(), msgDTO.getNewText());
		return updateMsgObj.getText();
	}
	
	@ResponseBody
	@GetMapping("/messages/api/page")
	public Page<Messages> showMessageApi(@RequestParam(name="p",defaultValue = "1")Integer pageNumber){
		Page<Messages> page = mService.findByPage(pageNumber);
		return page;
	}
	
	@GetMapping("/photo/ajaxUpload")
	public String ajaxUpload() {
		return "photo/ajaxUploadPage";
	}

	@ResponseBody
	@PostMapping("/photo/ajaxPost")
	public String ajaxPost(@RequestParam("photoName") String photoName, @RequestParam("file") MultipartFile file) {
		GoodPhoto gp = new GoodPhoto();
		
		try {
			gp.setPhoteFile(file.getBytes());
			gpService.insertGoodPhoto(gp);
			
			return "上傳成功";
		} catch (IOException e) {
			e.printStackTrace();
			return "上傳失敗";
		}

	}
}
