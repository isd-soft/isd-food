package com.example.isdbackend.repository;

import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.projection.DeliveryPrice;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.projection.UserOrderView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.id, " +
            "       concat(cast(o.date as date), ' ', cast(o.date as time)) date, " +
            "       o.ordered, " +
            "       m.name      menuName, " +
            "       mt.type     menuType, " +
            "       mt.price, " +
            "       p.name      providerName " +
            "FROM orders o " +
            "         LEFT JOIN users u ON o.user_id = u.id " +
            "         LEFT JOIN menu_type mt ON o.menu_type_id = mt.id " +
            "         LEFT JOIN menu m ON mt.menu_id = m.id " +
            "         LEFT JOIN provider p on m.provider_id = p.id " +
            "WHERE (:userId = 0 OR u.id = :userId) " +
            "AND (:#{#orderFilter.dateFrom} = '0' OR cast(o.date as date) >= cast(:#{#orderFilter.dateFrom} as date)) " +
            "AND (:#{#orderFilter.dateTo} = '0' OR cast(o.date as date) <= cast(:#{#orderFilter.dateTo} as date)) " +
            "AND ((:#{#orderFilter.providers}) IS NULL OR p.name IN (:#{#orderFilter.providers})) " +
            "GROUP BY o.id, m.id, p.image, p.id,mt.type,mt.price", nativeQuery = true)
    Page<OrderView> findAllBy(Pageable pageable, Long userId, OrderFilter orderFilter);

    OrderFullView findAllById(long orderId);

    List<Order> findAll();

    @Query(value = "SELECT u.id userId, " +
            "       cast(o.date as date), " +
            "       mt.price,p.id providerId " +
            "FROM orders o " +
            "         LEFT JOIN users u ON o.user_id = u.id " +
            "         LEFT JOIN menu_type mt on mt.id = o.menu_type_id " +
            "         LEFT JOIN menu m ON mt.menu_id = m.id " +
            "         LEFT JOIN provider p on m.provider_id = p.id " +
            "WHERE (:#{#orderFilter.dateFrom} = '0' OR cast(o.date as date) >= cast(:#{#orderFilter.dateFrom} as date)) " +
            "AND (:#{#orderFilter.dateTo} = '0' OR cast(o.date as date) <= cast(:#{#orderFilter.dateTo} as date)) " +
            "AND (ordered = :#{#orderFilter.ordered}) " +
            "ORDER BY u.id,o.date", nativeQuery = true)
    List<UserOrderView> findUsersOrders(OrderFilter orderFilter);

    @Query(value = "SELECT cast(o.date as date) , p.id providerId, p.delivery_price/count(DISTINCT o.user_id) deliveryPrice " +
            "FROM orders o " +
            "         LEFT JOIN menu_type mt on mt.id = o.menu_type_id " +
            "         LEFT JOIN menu m ON mt.menu_id = m.id " +
            "         LEFT JOIN provider p on m.provider_id = p.id " +
            "WHERE (:#{#orderFilter.dateFrom} = '0' OR cast(o.date as date) >= cast(:#{#orderFilter.dateFrom} as date)) " +
            "AND (:#{#orderFilter.dateTo} = '0' OR cast(o.date as date) <= cast(:#{#orderFilter.dateTo} as date)) " +
            "AND (ordered = :#{#orderFilter.ordered}) " +
            "GROUP BY cast(o.date as date), p.id", nativeQuery = true)
    List<DeliveryPrice> findOrderDeliveryPrice(OrderFilter orderFilter);
}
