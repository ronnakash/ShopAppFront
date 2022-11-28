package com.example.shopappfront.data.models;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import com.example.shopappfront.data.AuthenticationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends ApplicationModelWithId {

    private long time;
    List<OrderedItems> orderedItems;
    String status;
    BigDecimal orderTotalSum;
    private int userId;

    public Order(int orderId, long time, List<OrderedItems> orderedItem, String status, int userId) {
        super(orderId);
        this.userId = userId;
        this.time = time;
        this.orderedItems = orderedItem == null ? new ArrayList<>() : orderedItem;
        this.status = status;
        orderTotalSum = new BigDecimal(0);
    }

    public Order() {
        super(-1);
        orderedItems = new ArrayList<>();
        userId = AuthenticationRepository.getInstance().getUserId();
        status = "Pending";
        orderTotalSum = new BigDecimal(0);
    }


    public String getTimeString() {
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public long getTime() {
        return time;
    }

    public List<OrderedItems> getOrderedItems() {
        return orderedItems;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getOrderTotalSum() {
        orderTotalSum = new BigDecimal(0);
        for (OrderedItems items : orderedItems)
            orderTotalSum = orderTotalSum.add(items.getTotalPrice());
        return orderTotalSum;
    }

    public OrderedItems getOrderedItemsByItemId(int id){
        for (OrderedItems oi : orderedItems){
            if (oi.getItem().id==id)
                return oi;
        }
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }


    public boolean sameOrderedItemsAs(List<OrderedItems> otherOrderedItems){
        if (this.orderedItems.size() != otherOrderedItems.size())
            return false;
        for (int i = 0; i < orderedItems.size(); i++) {
            OrderedItems orderedItems1 = orderedItems.get(i);
            OrderedItems orderedItems2 = otherOrderedItems.get(i);
            if (orderedItems1.getItem().getId() != orderedItems2.getItem().getId() ||
                    orderedItems1.getQuantity() != orderedItems2.getQuantity() )
                return false;
        }
        return true;
    }


}
