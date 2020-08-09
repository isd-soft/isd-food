package com.example.isdbackend.repository;

import com.example.isdbackend.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository  extends CrudRepository<Item,Long> {
}
