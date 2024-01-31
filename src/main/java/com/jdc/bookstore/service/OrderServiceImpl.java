package com.jdc.bookstore.service;

import com.jdc.bookstore.entities.OrderDetail;
import com.jdc.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDetail create(OrderDetail order) {
        return orderRepository.save(order);
    }
}
