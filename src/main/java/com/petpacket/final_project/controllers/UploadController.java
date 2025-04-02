package com.petpacket.final_project.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
	@Autowired
	private Cloudinary cloudinary;

	@PostMapping("")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			String url = (String) uploadResult.get("secure_url");
			return ResponseEntity.ok(Map.of("url", url));
		} catch (IOException e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
