package com.example.medecin_v1.repository;


import com.example.medecin_v1.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    @Query("select new Document(d.FileId,d.name,d.size) From Document d ORDER BY d.uploadTime DESC")
    List<Document> findAll();
}
