package com.darmokhval.crm_programming_school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTPairResponse {
    private String accessToken;
    private String refreshToken;
}
