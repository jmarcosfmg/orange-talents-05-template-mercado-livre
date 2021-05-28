package com.orangetalents.treinomercadolivre.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequest {
	
	@NotNull
	@Size(min = 1)
	private List<MultipartFile> file = new ArrayList<>();

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public ImagemRequest(@NotNull @Size(min = 1) List<MultipartFile> file) {
		this.file = file;
	}
}
