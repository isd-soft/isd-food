package com.isdfood.isdproject.repositories;

import com.isdfood.isdproject.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Long> {
}
