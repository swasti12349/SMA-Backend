package com.sro.demo.service;

import com.sro.demo.entity.FileEntity;
import com.sro.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    @Autowired
    private FileRepository fileRepository;
    public void saveFile(String fileName, MultipartFile file) throws IOException {
        FileEntity files = new FileEntity();
        files.setFileName(fileName);
        files.setFile(file.getBytes());
//        fileRepository.saveFileToDB(files.getFileName(), files.getFile());
    }

}