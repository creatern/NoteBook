package com.captin.boot.data.repository;

import com.captin.boot.data.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer>, JpaSpecificationExecutor<Keyword> {
}
