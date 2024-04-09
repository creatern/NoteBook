package com.captin.boot.controller;

import com.captin.boot.data.entity.Document;
import com.captin.boot.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class HomeController {
    // 注入 文档服务
    @Autowired
    private DocumentService documentService;

    /* 响应 文档图 */
    @GetMapping("/documents/graph")
    public List<Document> documentsGraph() {
        return documentService.documentGraph();
    }
}
