package com.example.isdbackend.repository;

import com.example.isdbackend.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;

public interface MenuItemRepository extends CrudRepository<Item, Long> {
}
