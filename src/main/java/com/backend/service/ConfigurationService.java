package com.backend.service;

import com.backend.model.Configuration;
import com.backend.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    //Add new configuration to the database
    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    //Retrieve all configurations
    public List<Configuration> configPrint() {
        return configurationRepository.findAll();
    }

    //Retrieve the latest configuration
    public Configuration getLatestConfiguration() {
        return configurationRepository.findAll().get(0);
    }


}
