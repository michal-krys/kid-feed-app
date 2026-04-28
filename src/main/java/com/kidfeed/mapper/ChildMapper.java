package com.kidfeed.mapper;

import com.kidfeed.dto.request.ChildCreateRequest;
import com.kidfeed.dto.request.ChildUpdateRequest;
import com.kidfeed.dto.response.ChildResponse;
import com.kidfeed.entity.Child;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChildMapper {

    public Child toEntity(ChildCreateRequest request) {
        Child child = new Child();
        child.setName(request.getName());
        child.setBirthDate(request.getBirthDate());
        child.setWeightKg(request.getWeightKg());
        child.setDietStage(request.getDietStage());
        child.setDailyCalorieGoal(request.getDailyCalorieGoal());
        return child;
    }

    public void updateEntity(Child child, ChildUpdateRequest request) {
        child.setName(request.getName());
        child.setBirthDate(request.getBirthDate());
        child.setWeightKg(request.getWeightKg());
        child.setDietStage(request.getDietStage());
        child.setDailyCalorieGoal(request.getDailyCalorieGoal());
    }

    public ChildResponse toResponse(Child child) {
        ChildResponse response = new ChildResponse();
        response.setId(child.getId());
        response.setName(child.getName());
        response.setBirthDate(child.getBirthDate());
        response.setWeightKg(child.getWeightKg());
        response.setDietStage(child.getDietStage());
        response.setDailyCalorieGoal(child.getDailyCalorieGoal());
        response.setCreatedAt(child.getCreatedAt());
        response.setUpdatedAt(child.getUpdatedAt());
        return response;
    }
}
