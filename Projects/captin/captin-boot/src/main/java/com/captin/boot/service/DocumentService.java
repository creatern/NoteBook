package com.captin.boot.service;

import com.captin.boot.data.entity.Document;
import com.captin.boot.data.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentService {

    /* 获取文档图谱 */
    List<Document> documentGraph();
}
