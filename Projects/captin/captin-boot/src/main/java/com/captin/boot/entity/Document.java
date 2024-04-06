package com.captin.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "repositories_id")
    private String repId;
    @Column(name = "title")
    private String title;
    @Column(name = "context")
    private String context;
    @Column(name="_level")
    private Integer level;
    @Column(name = "_order")
    private Integer order;
}
