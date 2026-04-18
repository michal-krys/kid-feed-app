package com.kidfeed.kid_feed_app.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildUpdateRequest {

    private String name;
    private LocalDate birthDate;
    private BigDecimal weightKg;
    private String dietStage;
    private Integer dailyCalorieGoal;
}
