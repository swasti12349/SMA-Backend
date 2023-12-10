package com.sro.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import com.sro.demo.entity.FileMetaData;
import com.sro.demo.repository.FileRepository;
import com.sro.demo.service.FileService;
import com.sro.demo.service.FileUploadService;
import io.swagger.v3.core.util.Json;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/api/files")
public class FileController {


    @Autowired
    private FileService fileService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileRepository fileMetaDataRepository;

    @PostMapping("/uploadFileToServer")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("schoolName") String schoolName,
                                                   @RequestParam("className") String className,
                                                   @RequestParam("sectionName") String sectionName,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("fileName") String fileName
    ) {
        try {
            fileService.saveFile(file, schoolName, className, sectionName, type, fileName);
            FileMetaData fileMetaData = new FileMetaData(fileName, new Date());
            fileMetaDataRepository.save(fileMetaData);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
    }

    @PostMapping("/getSyllabus")
    public ResponseEntity<byte[]> getFile(

            @RequestParam("schoolName") String schoolName,
            @RequestParam("className") String className,
            @RequestParam("sectionName") String sectionName,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileType") String fileType
    ) {
        try {

            String folderName = "D:\\SMA" + "\\" + schoolName + "\\" + className + "\\" + sectionName + "\\" + fileType;
            String filePath = fileService.getFile(folderName);
            byte[] fileContent = Files.readAllBytes(Path.of(filePath));

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName + ".pdf");
            headers.setContentLength(fileContent.length);

//            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/getAllFiles")
    public ResponseEntity<Object> getAllFiles(@RequestParam("schoolName") String schoolName,
                                              @RequestParam("className") String className,
                                              @RequestParam("sectionName") String sectionName) {

        try {

            String folderName = schoolName + "\\" + className + "\\" + sectionName;

            List<FileMetaData> fileMap = new ArrayList<>();
            fileMap.addAll(fileService.getAllFilesInFolder(folderName));
            return ResponseEntity.ok(fileMap);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }

    @PostMapping("/uploadFileToDB")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.saveFile(file.getOriginalFilename(),
                    file);
            return ResponseEntity.ok("File Uploaded Successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
