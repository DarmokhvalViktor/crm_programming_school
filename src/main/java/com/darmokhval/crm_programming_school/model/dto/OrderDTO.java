package com.darmokhval.crm_programming_school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Integer age;
    private String course;
    private String courseFormat;
    private String courseType;
    private Integer sum;
    private Integer alreadyPaid;
    private LocalDateTime createdAt;
    private String utm;
    private String msg;
    private String status;
}
