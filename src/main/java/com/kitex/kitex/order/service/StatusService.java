package com.kitex.kitex.order.service;

import com.kitex.kitex.order.Status;
import com.kitex.kitex.order.repository.IStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final IStatusRepository statusRepository;

    public Status findStatusById(Integer id) {
        Optional<Status> status = this.statusRepository.findById(id);
        if(status.isEmpty()) {
            new ChangeSetPersister.NotFoundException();
        }

        return status.get();
    }
}
