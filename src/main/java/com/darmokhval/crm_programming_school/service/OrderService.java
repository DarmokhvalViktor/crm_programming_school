package com.darmokhval.crm_programming_school.service;

import com.darmokhval.crm_programming_school.mapper.OrderMapper;
import com.darmokhval.crm_programming_school.model.dto.OrderDTO;
import com.darmokhval.crm_programming_school.model.entity.Order;
import com.darmokhval.crm_programming_school.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Page<OrderDTO> getOrders(int page, int size, String order) {
        Sort sort = getSortOrder(order);
        Pageable pageable = PageRequest.of(page -1, size, sort);

        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = orderPage
                .getContent()
                .stream()
                .map(orderMapper::toOrderDTO)
                .toList();

        return new PageImpl<>(orderDTOList, pageable, orderPage.getTotalElements());
    }

    private Sort getSortOrder(String order) {
        if(order.startsWith("-")) {
            return Sort.by(order.substring(1)).descending();
        } else {
            return Sort.by(order).ascending();
        }
    }
}
