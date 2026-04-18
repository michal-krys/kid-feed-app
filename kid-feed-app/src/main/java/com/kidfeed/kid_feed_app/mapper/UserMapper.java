package com.kidfeed.kid_feed_app.mapper;

import com.kidfeed.kid_feed_app.dto.request.UserCreateRequest;
import com.kidfeed.kid_feed_app.dto.response.UserResponse;
import com.kidfeed.kid_feed_app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
