package com.isdfood.isdproject.repositories;

import com.isdfood.isdproject.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
