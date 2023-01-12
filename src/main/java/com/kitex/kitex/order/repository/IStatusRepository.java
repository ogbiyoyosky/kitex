package com.kitex.kitex.order.repository;

import com.kitex.kitex.order.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByStatusName(String statusName);
}
