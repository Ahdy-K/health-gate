package com.example.medecin_v1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MedicalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToMany
    private List<Document> Content;

    @OneToOne
    private User user;



}
