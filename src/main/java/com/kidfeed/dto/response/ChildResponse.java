package com.kidfeed.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildResponse {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private BigDecimal weightKg;
    private String dietStage;
    private Integer dailyCalorieGoal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
