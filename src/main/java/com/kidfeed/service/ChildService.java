package com.kidfeed.service;

import com.kidfeed.dto.request.ChildCreateRequest;
import com.kidfeed.dto.request.ChildUpdateRequest;
import com.kidfeed.dto.response.ChildResponse;
import com.kidfeed.entity.Child;
import com.kidfeed.entity.User;
import com.kidfeed.exception.AccessDeniedException;
import com.kidfeed.exception.ResourceNotFoundException;
import com.kidfeed.mapper.ChildMapper;
import com.kidfeed.repository.ChildRepository;
import com.kidfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final UserRepository userRepository;
    private final ChildMapper childMapper;

    public ChildResponse createChild(Long userId, ChildCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User " + userId + " not found"
                ));
        Child child = childMapper.toEntity(request);
        child.setUser(user);
        Child savedChild = childRepository.save(child);
        return childMapper.toResponse(savedChild);
    }

    public ChildResponse getChildById(Long userId, Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Child " + childId + " not found"
                ));
        if (!child.getUser().getId().equals(userId)) {
            throw new AccessDeniedException(
                    "Access to child " + childId + " denied"
            );
        }
        return childMapper.toResponse(child);
    }

    public List<ChildResponse> getChildrenByUser(Long userId) {
        return childRepository.findByUserId(userId)
                .stream()
                .map(childMapper::toResponse)
                .toList();
    }

    public ChildResponse updateChild(Long userId, Long childId, ChildUpdateRequest request) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Child " + childId + " not found"
                ));
        if (!child.getUser().getId().equals(userId)) {
            throw new AccessDeniedException(
                    "Access to child " + childId + " denied"
            );
        }
        childMapper.updateEntity(child, request);
        Child updatedChild = childRepository.save(child);
        return childMapper.toResponse(updatedChild);
    }

    public void deleteChild(Long userId, Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Child " + childId + " not found"
                ));
        if (!child.getUser().getId().equals(userId)) {
            throw new AccessDeniedException(
                    "Access to child " + childId + " denied"
            );
        }
        childRepository.delete(child);
    }
}
