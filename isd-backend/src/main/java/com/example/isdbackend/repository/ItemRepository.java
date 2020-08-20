package com.example.isdbackend.repository;

import com.example.isdbackend.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository  extends CrudRepository<Item,Long> {


}
