package com.example.isdbackend.repository;

import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Provider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {
    List<Provider> findAll();

    @Query(value = "SELECT name FROM provider", nativeQuery = true)
    List<String> findAllBy();

    @Query(value = "SELECT p.name " +
            "FROM orders o " +
            "         LEFT JOIN menu_type mt ON mt.id = o.menu_type_id " +
            "         LEFT JOIN menu m on m.id = mt.menu_id " +
            "         LEFT JOIN provider p on p.id = m.provider_id " +
            " " +
            "WHERE (o.user_id = :userId) " +
            "AND (:#{#orderFilter.dateFrom} = '0' OR cast(o.date as date) >= cast(:#{#orderFilter.dateFrom} as date)) " +
            "AND (:#{#orderFilter.dateTo} = '0' OR cast(o.date as date) <= cast(:#{#orderFilter.dateTo} as date)) " +
            "AND (ordered = :#{#orderFilter.ordered}) " +
            "GROUP BY p.name", nativeQuery = true)
    List<String> findAvailableProviders(long userId, OrderFilter orderFilter);
}
