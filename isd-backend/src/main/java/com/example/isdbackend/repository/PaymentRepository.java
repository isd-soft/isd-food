package com.example.isdbackend.repository;

import com.example.isdbackend.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM payment WHERE (:userId = 0 OR user_id = :userId) AND EXTRACT(YEAR FROM date) = :year AND EXTRACT(MONTH FROM date) = :month ", nativeQuery = true)
    Page<Payment> findAllByDateMonthAndDateYear(int month, int year, long userId, Pageable pageable);
}
