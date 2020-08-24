package com.example.isdbackend.service;


import com.example.isdbackend.dto.FileResource;
import com.example.isdbackend.dto.PaymentDataDTO;
import com.example.isdbackend.dto.UserPaymentData;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Payment;
import com.example.isdbackend.projection.DeliveryPrice;
import com.example.isdbackend.projection.UserIdAndNameView;
import com.example.isdbackend.projection.UserOrderView;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.PaymentRepository;
import com.example.isdbackend.repository.UserRepository;
import com.example.isdbackend.service.payment.PaymentCalculator;
import com.example.isdbackend.service.payment.PaymentExporter;
import com.example.isdbackend.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class PaymentService extends PaymentCalculator {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentExporter paymentExporter;

    private final UserService userService;

    /**
     * @param month - from which month select the payments
     * @param year  - from which year select the payments
     * @return a file with payment data selected from database
     */
    public FileResource exportMonthlyPayment(Integer month, Integer year) {
        Page<Payment> payments = paymentRepository.findAllByDateMonthAndDateYear(month, year, 0L, null);

        return paymentExporter.exportMonthly(payments.getContent(), DateUtil.getDateFromDateTime(payments.getContent().get(0).getDate()));
    }

    public FileResource exportPayment(OrderFilter orderFilter) {
        PaymentDataDTO paymentData = getAllPaymentData(orderFilter, null, false);

        return paymentExporter.exportMonthly(paymentData.getUserPayments(), paymentData.getDateFrom() + "/" + paymentData.getDateTo());
    }

    /**
     * @param month - from which month select the payments
     * @param year  - from which year select the payments
     * @return payment data in json
     */
    public Page<Payment> getMonthlyPaymentData(Integer month, Integer year, Pageable pageable, boolean forCurrentUser) {
        return paymentRepository.findAllByDateMonthAndDateYear(month, year, forCurrentUser ? userService.getCurrentUserId() : 0L, pageable);
    }

    public Payment getUserMonthlyPaymentData(Integer month, Integer year) {
        Page<Payment> payments = this.getMonthlyPaymentData(month, year, null, true);
        if (payments.getContent().size() == 0) return null;

        return payments.getContent().get(0);
    }

    public UserPaymentData getUserPaymentData(OrderFilter orderFilter) {
        UserPaymentData userPaymentData = this.getAllPaymentData(orderFilter, null, true).getUserPayments().get(0);
        return userPaymentData;
    }

    public PaymentDataDTO getAllPaymentData(OrderFilter orderFilter, Pageable pageable, boolean forCurrentUser) {

        if (pageable == null)
            pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by("users.employment_date"));

        orderFilter.setOrdered(true);

        PaymentDataDTO paymentDataDTO = new PaymentDataDTO();
        if (!orderFilter.getDateFrom().equals("0"))
            paymentDataDTO.setDateFrom(orderFilter.getDateFrom());
        if (!orderFilter.getDateTo().equals("0"))
            paymentDataDTO.setDateTo(orderFilter.getDateTo());
        else paymentDataDTO.setDateTo(DateUtil.getDateFromDateTime(new Date()));

        List<UserPaymentData> userPaymentDataList = new ArrayList<>();

        long currentUserId = forCurrentUser ? userService.getCurrentUserId() : 0L;

        Page<UserIdAndNameView> usersPage = userRepository.findAllBy(currentUserId, pageable);
        paymentDataDTO.setTotalPages(usersPage.getTotalPages());

        List<UserIdAndNameView> users = usersPage.getContent();
        List<UserOrderView> usersOrders = orderRepository.findUsersOrders(orderFilter);
        Map<String, Integer> deliveryPricesMap = getDeliveryPriceByDateAndProvider(orderFilter);

        int ordersCount = usersOrders.size();

        for (UserIdAndNameView user : users) {
            UserPaymentData userPaymentData = new UserPaymentData(Math.toIntExact(user.getId()), 0, user.getFullName());

            for (int i = 0; i < ordersCount; i++) {
                UserOrderView userOrder = usersOrders.get(i);
                if (user.getId() != userOrder.getUserId()) {
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
        paymentDataDTO.setUserPayments(userPaymentDataList);

        return paymentDataDTO;
    }

    private boolean areTheSameDates(Date d1, Date d2) {
        return d1.equals(d2);
    }

    /**
     * @param orderFilter
     * @return a map with key as order date and provider id, and value is the delivery price
     */
    private Map<String, Integer> getDeliveryPriceByDateAndProvider(OrderFilter orderFilter) {
        Map<String, Integer> deliveryPricesMap = new HashMap<>();
        List<DeliveryPrice> deliveryPrices = orderRepository.findOrderDeliveryPrice(orderFilter);

        for (DeliveryPrice deliveryPrice : deliveryPrices) {
            deliveryPricesMap.put(deliveryPrice.getDate().toString() + deliveryPrice.getProviderId(), deliveryPrice.getDeliveryPrice());
        }

        return deliveryPricesMap;
    }

    public List<String> getAvailableMonths() {
        return paymentRepository.findAvailableMonths();
    }

}
