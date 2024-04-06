package com.captin.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clip {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="documents_id")
    private Integer docId;
    @Column(name="title")
    private String title;
    @Column(name="_level")
    private Integer level;
    @Column(name="order")
    private Integer order;
    @Column(name="context")
    private String context;
}
