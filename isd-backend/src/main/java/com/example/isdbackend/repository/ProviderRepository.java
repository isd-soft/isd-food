package com.example.isdbackend.repository;

import com.example.isdbackend.model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Integer> {

}
