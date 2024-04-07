package com.captin.boot.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keywords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "keyword")
    private String keyword;
    @Column(name = "context")
    private String context;
    @Column(name = "links")
    private String links;
}
