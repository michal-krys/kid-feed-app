package com.kidfeed.kid_feed_app.service;

import com.kidfeed.kid_feed_app.dto.request.UserCreateRequest;
import com.kidfeed.kid_feed_app.dto.response.UserResponse;
import com.kidfeed.kid_feed_app.entity.User;
import com.kidfeed.kid_feed_app.exception.DuplicateResourceException;
import com.kidfeed.kid_feed_app.exception.ResourceNotFoundException;
import com.kidfeed.kid_feed_app.mapper.UserMapper;
import com.kidfeed.kid_feed_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User with email " + request.getEmail() + " already exists");
        }
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User " + id + " not found"
                ));
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
