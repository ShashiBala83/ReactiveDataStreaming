package com.demo.reactive.Controllers;

import com.demo.reactive.Services.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "elasticSearch")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    private List<Map<String, Object>> getAllData(@RequestParam(required = true) String index) {
        return elasticSearchService.findAll(index);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json")
    private void createData(@RequestParam String index, @RequestBody HashMap<String, Object> data) {
        try {
            elasticSearchService.insertDocument(index, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
