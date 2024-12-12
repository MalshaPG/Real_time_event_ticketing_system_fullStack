package com.backend.repository;

import com.backend.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    //Jpa repository provides save(), findAll(), findById() operations
}


