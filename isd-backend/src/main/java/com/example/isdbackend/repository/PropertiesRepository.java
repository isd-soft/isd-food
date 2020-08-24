package com.example.isdbackend.repository;

import com.example.isdbackend.model.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface PropertiesRepository extends JpaRepository<Properties, String> {

    @Query(value = "SELECT cast(value as date) FROM properties WHERE id = 'last_order' LIMIT 1", nativeQuery = true)
    Date findLastOrderDate();

}
