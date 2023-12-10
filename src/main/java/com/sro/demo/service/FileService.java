package com.sro.demo.service;

import com.sro.demo.entity.FileMetaData;
import com.sro.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    private final String uploadDir = "D:\\SMA";
    @Autowired
    private FileRepository fileRepository;
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("application/pdf");

    public void saveFile(MultipartFile file, String schoolName, String className, String sectionName,
                         String type,
                         String fileName) {
        try {

            Path uploadPath = Paths.get(uploadDir, schoolName, className, sectionName, type).toAbsolutePath().normalize();

            if (!isAllowedContentType(file.getContentType())) {
                throw new IllegalArgumentException("Invalid file type. Only PDF files are allowed.");
            }

            Files.createDirectories(uploadPath);

            Path targetLocation = uploadPath.resolve(fileName);

            if (Files.exists(targetLocation)) {
                Files.delete(targetLocation);
                Files.copy(file.getInputStream(), targetLocation);
            } else {
                Files.copy(file.getInputStream(), targetLocation);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private boolean isAllowedContentType(String contentType) {
        return ALLOWED_CONTENT_TYPES.contains(contentType);
    }

    public String getFile(String folderName) {
        Path filePath = Paths.get(folderName);

        try {
            File file = filePath.toFile();
//            if (!file.exists()) {
//                throw new IllegalArgumentException("File does not exist: " + filePath);
//            }

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<FileMetaData> getAllFilesInFolder(String filePath) {
        List<FileMetaData> fileList = new ArrayList<>();

        try {
            Path folderPath = Paths.get(uploadDir, filePath).toAbsolutePath().normalize();

            if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
                throw new IllegalArgumentException("Invalid folder: " + folderPath);
            }

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
                for (Path fp : directoryStream) {
                    BasicFileAttributes fileAttributes = Files.readAttributes(fp, BasicFileAttributes.class);

                    String fileName = fp.getFileName().toString();
                    Date creationTime = convertToDate(fileAttributes.creationTime());

                    FileMetaData fileMetaData = new FileMetaData(fileName, creationTime);

                    fileRepository.save(fileMetaData);
                    fileList.add(fileMetaData);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return fileList;
    }

    private Date convertToDate(FileTime fileTime) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.parse(fileTime.toString());
    }

}
