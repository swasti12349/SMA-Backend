package com.sro.demo.repository;

import com.sro.demo.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {
     @Query("SELECT s FROM School s WHERE s.SchoolCode = :schoolCode")
     School findBySchoolCode(@Param("schoolCode") String schoolCode);

}
