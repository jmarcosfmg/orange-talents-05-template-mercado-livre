package com.orangetalents.treinomercadolivre.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploader {
	
	private String connection;
	
	public ImageUploader() {
		this.connection = "https://www.imageserver.com/product/";
	}
	
	public String sendImage(Long id, MultipartFile imagem) {
		return this.connection+id+"/"+imagem.getName();
	}

}
