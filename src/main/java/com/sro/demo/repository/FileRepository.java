package com.sro.demo.repository;

import com.sro.demo.entity.FileEntity;
import com.sro.demo.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FileRepository extends JpaRepository<FileMetaData, Long> {


}