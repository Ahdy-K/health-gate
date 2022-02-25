package com.example.medecin_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long  FileId;

    private String  name;

    private long size;

    private Date uploadTime;

    private byte[] content;

    public Document(Long fileId, String name, long size) {
        FileId = fileId;
        this.name = name;
        this.size = size;
    }

    @ManyToOne
    private MedicalFile medicalfile;



}
