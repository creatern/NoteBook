package com.captin.boot.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "context")
    private String context;
    @Column(name = "create_time")
    private Date createTime;
}
