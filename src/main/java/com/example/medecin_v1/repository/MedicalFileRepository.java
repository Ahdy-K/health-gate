package com.example.medecin_v1.repository;

import com.example.medecin_v1.entity.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFileRepository extends JpaRepository<MedicalFile,Long> {
}
