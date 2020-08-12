package com.example.isdbackend.repository;

import com.example.isdbackend.model.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, Long> {
    List<Provider> findAll();
}
