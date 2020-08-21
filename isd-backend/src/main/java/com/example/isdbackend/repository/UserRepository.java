package com.example.isdbackend.repository;

import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.UserIdAndNameView;
import com.example.isdbackend.projection.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    UserView findAllById(Long id);

    Page<UserView> findAllBy(Pageable pageable);

    @Query(countQuery = "SELECT COUNT(id) FROM users WHERE :userId = 0 OR id = :userId",
            value = "SELECT id,first_name firstName,last_name lastName " +
                    "FROM users " +
                    "WHERE :userId = 0 OR id = :userId ", nativeQuery = true)
    Page<UserIdAndNameView> findAllBy(long userId, Pageable pageable);
}
