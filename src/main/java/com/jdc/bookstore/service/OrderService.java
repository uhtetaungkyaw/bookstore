package com.jdc.bookstore.service;


import com.jdc.bookstore.entities.OrderDetail;

public interface OrderService {
    OrderDetail create(OrderDetail order);
}
