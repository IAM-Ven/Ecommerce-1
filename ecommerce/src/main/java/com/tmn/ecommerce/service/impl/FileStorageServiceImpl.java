package com.tmn.ecommerce.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tmn.ecommerce.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path root = Paths.get("api/image/images/item/");

	@Override
	public void init() {

	}

	@Override
	public void save(MultipartFile file) {
		// String fileName = file.getOriginalFilename();
//		File subFile = new File(mainFile,FilenameUtils.getBaseName(fileName));
//		subFile.mkdir();
//		if (!subFile.exists()) {
//			subFile.mkdir();
//		}
		// System.out.println(fileName);

//		String modifiedName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()
//								+"."+FilenameUtils.getExtension(fileName);
//		String modifiedName = FilenameUtils.getBaseName(fileName)
//								+"."+FilenameUtils.getExtension(fileName);
		// System.out.println(modifiedName);
		// File serverFile = new File(file+File.separator+modifiedName);
		String modfiedName = FilenameUtils.getBaseName(file.getOriginalFilename()) + "_" + System.currentTimeMillis()
				+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			System.out.println(modfiedName);
			Files.copy(file.getInputStream(), this.root.resolve(modfiedName));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

}
