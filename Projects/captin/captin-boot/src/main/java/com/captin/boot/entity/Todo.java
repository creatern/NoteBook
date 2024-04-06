package com.captin.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="context")
    private String context;
    @Column(name = "state")
    private Integer state;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
}
