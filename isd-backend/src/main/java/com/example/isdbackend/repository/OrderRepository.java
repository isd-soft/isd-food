package com.example.isdbackend.repository;

import com.example.isdbackend.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {

}
