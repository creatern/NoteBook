package com.captin.boot.data.repository;

import com.captin.boot.data.entity.Clip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Integer>, JpaSpecificationExecutor<Clip> {
}
