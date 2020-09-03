package com.example.isdbackend.repository;

import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.UserIdAndNameView;
import com.example.isdbackend.projection.UserView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    UserView findAllById(Long id);

    Page<UserView> findAllBy(Pageable pageable);

    @Query(countQuery = "SELECT COUNT(id) FROM users WHERE :userId = 0 OR id = :userId",
            value = "SELECT id,first_name firstName,last_name lastName " +
                    "FROM users " +
                    "WHERE :userId = 0 OR id = :userId ", nativeQuery = true)
    Page<UserIdAndNameView> findAllBy(long userId, Pageable pageable);

    @Query(value = "SELECT u.id," +
            "email," +
            "first_name firstName," +
            "last_name lastName," +
            "employment_date employmentDate," +
            "enabled," +
            "skype_id skypeId," +
            "r.name roleName " +
            "FROM users u " +
            "LEFT JOIN users_roles ur ON u.id = ur.user_id " +
            "LEFT JOIN role r ON r.id = ur.role_id " +
            "WHERE (first_name || ' ' || last_name) = :fullName " +
            "GROUP BY u.id,r.name,r.id", nativeQuery = true)
    UserView findAllByName(String fullName);

    @Query(value = "SELECT first_name || ' ' || last_name as name " +
            "FROM users " +
            "WHERE (lower(first_name) like '%' || :name || '%' OR lower(last_name) like '%' || :name || '%') " +
            "   OR :name like '%' || lower(first_name) || '%' " +
            "   OR :name like '%' || lower(last_name) || '%'", nativeQuery = true)
    List<String> search(String name);
}
