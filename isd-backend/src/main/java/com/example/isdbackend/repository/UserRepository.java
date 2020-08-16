package com.example.isdbackend.repository;

import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.UserIdAndNameView;
import com.example.isdbackend.projection.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    UserView findAllById(Long id);

    @Query(value = "SELECT id,first_name firstName,last_name lastName FROM users ORDER BY id", nativeQuery = true)
    List<UserIdAndNameView> findAllByOrderByIdAsc();
}
