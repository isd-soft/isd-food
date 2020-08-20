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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.id, " +
            "       concat(cast(o.date as date), ' ', cast(o.date as time)) date, " +
            "       o.ordered, " +
            "       o.user_id userId, "+
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
            "AND ( ordered = :#{#orderFilter.ordered}) " +
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

    @Query(value = "SELECT COUNT(id)\n" +
            "FROM orders o\n" +
            "WHERE cast(o.date as date) = cast(:orderDate as date)\n" +
            "  AND o.user_id = :userId", nativeQuery = true)
    int findOrdersCountByDateAndUserId(Date orderDate, long userId);

    @Query(value = "SELECT (extract(isodow from cast(:orderDate as date)) = m.day_of_week) " +
            "FROM menu_type mt " +
            "         JOIN menu m on m.id = mt.menu_id " +
            "WHERE mt.id = :menuTypeId ", nativeQuery = true)
    boolean findMenuDayEqualsOrderDay(Date orderDate, long menuTypeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE orders o\n" +
            "SET ordered = true\n" +
            "WHERE cast(o.date as date) = cast(:orderDate as date)", nativeQuery = true)
    void setOrdersAsPlaced(Date orderDate);
}
