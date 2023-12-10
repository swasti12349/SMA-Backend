package com.sro.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    @Lob
    @Column(length = 100000)
    private byte[] fileData;

    public FileEntity(String fileName, byte[] fileData) {
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public FileEntity() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return fileData;
    }

    public void setFile(byte[] file) {
        this.fileData = file;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
