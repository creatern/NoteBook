package com.captin.boot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<com.captin.boot.data.entity.Record, Integer>, JpaSpecificationExecutor<com.captin.boot.data.entity.Record> {
}
