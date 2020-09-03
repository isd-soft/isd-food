package com.example.isdbackend.repository;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.projection.MenuTypeView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuTypeRepository extends CrudRepository<MenuType, Long> {
    List<MenuType> findAll();

    List<MenuTypeView> findAllBy();
}
