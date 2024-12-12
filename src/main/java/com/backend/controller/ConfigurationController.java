package com.backend.controller;

import com.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.Configuration;
import java.util.List;

//Allow cross-origin request from the React front-end
@CrossOrigin(origins = "http://localhost:5173")
@RestController //Handle HTTP requests
@RequestMapping(path = "api/v1/configuration")
public class ConfigurationController {
    //Dependency injection of configurationService
    @Autowired
    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationservice) {
        //Assign the injection configuration service to the class member
        this.configurationService = configurationservice;
    }

    //Rest end points
    //Retrieve all the configurations from the database
    @GetMapping()
    public List<com.backend.model.Configuration> configPrint(){
        return configurationService.configPrint();
    }

    //Add new resources to the system(new configuration)
    @PostMapping
    public void addConfiguration(@RequestBody Configuration configuration){
        configurationService.saveConfiguration(configuration);
    }



}
