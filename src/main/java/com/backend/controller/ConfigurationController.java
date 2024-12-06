package com.backend.controller;

import com.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.Configuration;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/configuration")
public class ConfigurationController {
    @Autowired
    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationservice) {
        this.configurationService = configurationservice;
    }
    //rest end point
    @GetMapping()
    public List<com.backend.model.Configuration> configPrint(){
        return configurationService.configPrint();
    }

    //add new resources to the system(new configuration)
    @PostMapping
    public void addConfiguration(@RequestBody Configuration configuration){
        configurationService.saveConfiguration(configuration);
    }


}
