package com.kitex.kitex.order.repository;

import com.kitex.kitex.order.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<PlacedOrder, Integer> {
}
