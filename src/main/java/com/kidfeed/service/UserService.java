package com.kidfeed.service;

import com.kidfeed.dto.request.UserCreateRequest;
import com.kidfeed.dto.response.UserResponse;
import com.kidfeed.entity.User;
import com.kidfeed.exception.DuplicateResourceException;
import com.kidfeed.exception.ResourceNotFoundException;
import com.kidfeed.mapper.UserMapper;
import com.kidfeed.repository.UserRepository;
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
