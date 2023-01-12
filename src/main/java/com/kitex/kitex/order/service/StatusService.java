package com.kitex.kitex.order.service;

import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.order.entity.Status;
import com.kitex.kitex.order.repository.IStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final IStatusRepository statusRepository;

    public Status findStatusById(Integer id) {
        Optional<Status> status = this.statusRepository.findById(id);
        if(status.isEmpty()) {
            throw new NotFoundException("Status not found");
        }

        return status.get();
    }

    public List<Status> findStatuses() {
        List<Status> status = this.statusRepository.findAll();

        return status;
    }

    public Status findStatusByName(String statusName) {
        Optional<Status> status = this.statusRepository.findByStatusName(statusName);
        if(status.isEmpty()) {
            throw new NotFoundException("Status not found");
        }

        return status.get();
    }
}
