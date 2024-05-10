package com.darmokhval.crm_programming_school.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name shouldn't be empty!")
    @Column(name = "username")
    private String name;
//    TODO email and name uniqueness, when signing-in. Do we need unique name? Authentication utilize upon unique username, change to email?
    @NotEmpty(message = "Email shouldn't be empty!")
    @Email(message = "Invalid email pattern!")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Password shouldn't be empty!")
    @Column(name = "user_password")
    private String password;
    private String surname;
    @Column(name = "is_active")
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
