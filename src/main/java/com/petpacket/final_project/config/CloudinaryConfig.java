package com.petpacket.final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
	@Bean
	public Cloudinary cloudinary() {
		 return new Cloudinary(ObjectUtils.asMap(
		            "cloud_name", "degcsamx9",
		            "api_key", "719733478917128",
		            "api_secret", "XTs_FxgLPDLsBuMuXwBkNNZJdnU"
		        ));
	}

}
