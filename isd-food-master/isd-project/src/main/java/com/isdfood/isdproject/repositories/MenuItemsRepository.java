package com.isdfood.isdproject.repositories;

import com.isdfood.isdproject.model.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsRepository extends JpaRepository<MenuItems,Long> {
}
