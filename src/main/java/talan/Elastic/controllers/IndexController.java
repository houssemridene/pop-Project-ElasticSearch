package talan.Elastic.controllers;


import org.elasticsearch.cluster.metadata.IndexAbstraction.Index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import talan.Elastic.services.IndexService;






@RestController
@RequestMapping("/api/index")
public class IndexController {
    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service;
    }


    @PostMapping("/recreate")
    public void recreateAllIndices() {
        service.recreateIndices(true);
    }
}