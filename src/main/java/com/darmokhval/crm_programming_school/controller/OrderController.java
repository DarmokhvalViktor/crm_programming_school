package com.darmokhval.crm_programming_school.controller;

import com.darmokhval.crm_programming_school.model.dto.OrderDTO;
import com.darmokhval.crm_programming_school.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<Page<OrderDTO>> getOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "25") int size,
            @RequestParam(required = false, defaultValue = "-id") String order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(page, size, order));
    }
}
