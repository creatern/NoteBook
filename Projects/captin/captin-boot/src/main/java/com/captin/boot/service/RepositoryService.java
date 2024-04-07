package com.captin.boot.service;

import com.captin.boot.data.entity.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepositoryService {
    List<Repository> listRepository();
}
