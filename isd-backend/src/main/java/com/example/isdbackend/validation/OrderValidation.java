package com.example.isdbackend.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OrderValidation {

    private final Date orderDate;
    private final Date currentDate;

    private final int orderDateDay;
    private final int currentDateDay;

    public OrderValidation(Date orderDate, Date currentDate) {
        Calendar cal = Calendar.getInstance();

        this.orderDate = orderDate;
        this.currentDate = currentDate;

        cal.setTime(orderDate);
        this.orderDateDay = cal.get(Calendar.DAY_OF_WEEK);

        cal.setTime(currentDate);
        this.currentDateDay = cal.get(Calendar.DAY_OF_WEEK);
    }

    public String validate() {
        if (!isTheOrderForCurrentWeek()) return "You can make an order only for current week";
        else if (isTheCurrentDateOnWeekend()) return "You can not make an order from weekend";
        else if (!isTheOrderForCurrentOrNextDate()) return "You can not make an order for previous day";

        return null;
    }

    public boolean isTheOrderForCurrentWeek() {
        long diffInMillies = Math.abs(orderDate.getTime() - currentDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return (diff > 0 && diff < 5);
    }

    public boolean isTheOrderForCurrentOrNextDate() {
        return (currentDate.before(orderDate) || currentDate.equals(orderDate));
    }

    public boolean isTheOrderDateOnWeekend() {
        return orderDateDay < 6;
    }

    public boolean isTheCurrentDateOnWeekend() {
        return currentDateDay > 5;
    }

}
