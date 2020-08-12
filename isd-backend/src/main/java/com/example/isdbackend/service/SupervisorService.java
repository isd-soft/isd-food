package com.example.isdbackend.service;

import com.example.isdbackend.dto.UserPaymentData;
import com.example.isdbackend.projection.DeliveryPrice;
import com.example.isdbackend.projection.UserIdAndNameView;
import com.example.isdbackend.projection.UserOrderView;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.UserRepository;
import com.example.isdbackend.service.payment.PaymentCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class SupervisorService extends PaymentCalculator {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public List<UserPaymentData> getAllPaymentData() {

        List<UserPaymentData> userPaymentDataList = new ArrayList<>();

        List<UserIdAndNameView> users = userRepository.findAllByOrderByIdAsc();
        List<UserOrderView> usersOrders = orderRepository.findUsersOrders();
        Map<String, Integer> deliveryPricesMap = getDeliveryPriceByDateAndProvider();

        int ordersCount = usersOrders.size();

        for (UserIdAndNameView user : users) {
            UserPaymentData userPaymentData = new UserPaymentData(Math.toIntExact(user.getId()), 0, user.getFullName());

            for (int i = 0; i < ordersCount; i++) {
                UserOrderView userOrder = usersOrders.get(i);
                if (user.getId() != userOrder.getUserId()) {
                    if (user.getId() < userOrder.getUserId())
                        break;
                    continue;
                }

                UserOrderView nextUserOrder = i < ordersCount - 1 ? usersOrders.get(i + 1) : null;

                if (nextUserOrder != null && userOrder.getUserId() == nextUserOrder.getUserId()) {
                    // if user has 2 orders at the same date
                    if (areTheSameDates(userOrder.getDate(), nextUserOrder.getDate())) {
                        if (userOrder.getProviderId() == nextUserOrder.getProviderId()) {
                            userPaymentData.setPayment(userPaymentData.getPayment() + paymentCalc2(deliveryPricesMap.get(userOrder.getDate().toString() + userOrder.getProviderId()), userOrder, nextUserOrder));
                        } else {
                            userPaymentData.setPayment(userPaymentData.getPayment() + paymentCalc2(deliveryPricesMap.get(userOrder.getDate().toString() + userOrder.getProviderId()) + deliveryPricesMap.get(nextUserOrder.getDate().toString() + nextUserOrder.getProviderId()), userOrder, nextUserOrder));
                        }
                        i++;
                    } else {
                        userPaymentData.setPayment(userPaymentData.getPayment() + paymentCalc(deliveryPricesMap.get(userOrder.getDate().toString() + userOrder.getProviderId()), userOrder));
                    }
                } else {
                    userPaymentData.setPayment(userPaymentData.getPayment() + paymentCalc(deliveryPricesMap.get(userOrder.getDate().toString() + userOrder.getProviderId()), userOrder));
                }
            }
            userPaymentDataList.add(userPaymentData);
        }
        return userPaymentDataList;
    }

    private boolean areTheSameDates(Date d1, Date d2) {
        return d1.equals(d2);
    }

    private int ordersCountByProviderOnDate(List<UserOrderView> orders, UserOrderView userOrder) {
        int count = 1;

        for (UserOrderView order : orders) {
            if (order.getDate().equals(userOrder.getDate())
                    && order.getProviderId() == userOrder.getProviderId()
                    && userOrder.getUserId() != order.getUserId())
                count++;
        }
        return count;
    }

    private Map<String, Integer> getDeliveryPriceByDateAndProvider() {
        Map<String, Integer> deliveryPricesMap = new HashMap<>();
        List<DeliveryPrice> deliveryPrices = orderRepository.findDeliveryPrice();

        for (DeliveryPrice deliveryPrice : deliveryPrices) {
            deliveryPricesMap.put(deliveryPrice.getDate().toString() + deliveryPrice.getProviderId(), deliveryPrice.getDeliveryPrice());
        }

        return deliveryPricesMap;
    }

}
