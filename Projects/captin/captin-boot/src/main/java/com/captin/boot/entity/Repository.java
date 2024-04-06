package com.captin.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="repositories")
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="category")
    private String category;
}
