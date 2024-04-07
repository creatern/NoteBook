package com.captin.boot.controller;

import com.captin.boot.data.entity.Repository;
import com.captin.boot.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class HomeController {
    // 注入 仓库服务
    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("/doc/repository/list")
    public List<Repository> listRepository() {
        return repositoryService.listRepository();
    }
}
