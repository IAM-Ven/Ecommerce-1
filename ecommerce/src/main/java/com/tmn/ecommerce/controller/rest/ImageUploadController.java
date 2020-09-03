package com.tmn.ecommerce.controller.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tmn.ecommerce.dto.FileInfo;
import com.tmn.ecommerce.dto.MessageResponse;
import com.tmn.ecommerce.service.FileStorageService;

@RestController
@RequestMapping(path = "/api/image")
@CrossOrigin(origins = "http://localhost:4210")
public class ImageUploadController {

	@Autowired
	FileStorageService fileStorageService;

	 final String path = "api/image/images/items";
	@Autowired
	ServletContext context;

	@PostMapping(value = "/upload")
	public ResponseEntity<MessageResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		MessageResponse resp = new MessageResponse();
		System.out.println(files);
		try {
			List<String> fileNames = new ArrayList<String>();
			Arrays.asList(files).stream().forEach(file -> {
				fileStorageService.save(file);
				fileNames.add(file.getOriginalFilename());
			});
			resp.setMessage("Image Uploaded successfully" + fileNames);
		} catch (Exception e) {
			resp.setMessage("Image Uploaded failed" + e.getMessage());
			return new ResponseEntity<MessageResponse>(resp, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<MessageResponse>(resp, HttpStatus.ACCEPTED);
	}

	@PostMapping("/saveimage")
	public void uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {
		File mainFile = new File(path,"item");
		String fileName = file.getOriginalFilename();
		boolean isMainExit = mainFile.exists();
		if (!isMainExit) {
			mainFile.mkdir();
		}
//		File subFile = new File(mainFile,FilenameUtils.getBaseName(fileName));
//		subFile.mkdir();
//		if (!subFile.exists()) {
//			subFile.mkdir();
//		}
		
//		String modifiedName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()
//								+"."+FilenameUtils.getExtension(fileName);
		String modifiedName = FilenameUtils.getBaseName(fileName)
								+"."+FilenameUtils.getExtension(fileName);
		File serverFile = new File(mainFile+File.separator+modifiedName);
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if (imageRepository.save(img) != null) {
//			return new ResponseEntity<Response>(new Response("Image saved successfully"),HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Response>(new Response("Image saved Error"),HttpStatus.BAD_REQUEST);
//		}
//		Image img = new Image(modifiedName, file.getContentType(),compressBytes(file.getBytes()));
//		return imageRepository.save(img);
	}

	@GetMapping(value = "/images/{image}",produces = "image/jpg")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("image") String image) throws IOException {
 
		String url = path  + "item/"+ image;
		Resource imgFile = new UrlResource(Paths.get(url).toUri());
		
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

	@GetMapping(value = "/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(ImageUploadController.class, "getFile", path.getFileName().toString()).build()
					.toString();
			System.out.println(filename + " " + url);
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		System.out.println(filename);
		Resource file = fileStorageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[2048];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
