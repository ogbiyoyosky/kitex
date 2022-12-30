package com.kitex.kitex.order.repository;

import com.kitex.kitex.order.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status,Integer> {
}
