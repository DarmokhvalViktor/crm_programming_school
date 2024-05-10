package com.darmokhval.crm_programming_school.mapper;

import com.darmokhval.crm_programming_school.model.dto.OrderDTO;
import com.darmokhval.crm_programming_school.model.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ObjectMapper objectMapper;

    public OrderDTO toOrderDTO(Order order) {
        return objectMapper.convertValue(order, OrderDTO.class);
    }
    public Order toOrder(OrderDTO orderDTO) {
        return objectMapper.convertValue(orderDTO, Order.class);
    }
}
