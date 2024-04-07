package com.captin.boot.service.impl;

import com.captin.boot.data.entity.Repository;
import com.captin.boot.data.repository.RepositoryRepository;
import com.captin.boot.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    // 注入 Repository仓库
    @Autowired
    private RepositoryRepository repositoryRepository;
    @Override
    public List<Repository> listRepository() {
        return repositoryRepository.findAll();
    }
}
