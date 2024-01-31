package com.jdc.bookstore.repository;

import com.jdc.bookstore.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail,Integer> {
}
