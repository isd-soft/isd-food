package com.example.isdbackend.repository;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.projection.MenuView;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface MenuRepository extends CrudRepository<Menu,Long> {
    List<Menu> findByDayOfWeek(DayOfWeek day);
}
