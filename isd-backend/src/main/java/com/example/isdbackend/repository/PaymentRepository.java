package com.example.isdbackend.repository;

import com.example.isdbackend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM payment WHERE (:userId = 0 OR user_id = :userId) AND EXTRACT(YEAR FROM date) = :year AND EXTRACT(MONTH FROM date) = :month ", nativeQuery = true)
    List<Payment> findAllByDateMonthAndDateYear(int month, int year, long userId);
}
