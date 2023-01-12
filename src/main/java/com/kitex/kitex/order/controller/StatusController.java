package com.kitex.kitex.order.controller;

import com.kitex.kitex.order.service.StatusService;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAnyRole('KITCHEN_ADMIN', 'CUSTOMER','DRIVER')")
@RequestMapping(path = "/api/v1/statuses")
public class StatusController {
    private final StatusService statusService;
    @GetMapping
    public ResponseEntity<ResponseDto> getStatuses() {
        return Response.send("Successfully fetched all statuses",this.statusService.findStatuses(),200,true);
    }

}
