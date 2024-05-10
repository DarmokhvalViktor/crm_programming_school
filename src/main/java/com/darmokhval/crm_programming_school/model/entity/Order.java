package com.darmokhval.crm_programming_school.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name shouldn't be empty!")
    private String name;
    @NotEmpty(message = "Surname shouldn't be empty!")
    private String surname;
    @NotEmpty(message = "Email shouldn't be empty!")
    @Email(message = "Provided email not valid!")
    private String email;
    @NotEmpty(message = "Phone shouldn't be empty!")
    private String phone;
    @NotNull(message = "Age shouldn't be empty!")
    @Positive(message = "Age shouldn't be negative!")
    private Integer age;
    private String course;
    @Column(name = "course_format")
    private String courseFormat;
    @Column(name = "course_type")
    private String courseType;
    private Integer sum;
    @Column(name = "alreadyPaid")
    private Integer alreadyPaid;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String utm;
    private String msg;
    private String status;
}
