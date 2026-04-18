package com.kidfeed.kid_feed_app.controller;

import com.kidfeed.kid_feed_app.dto.request.ChildCreateRequest;
import com.kidfeed.kid_feed_app.dto.request.ChildUpdateRequest;
import com.kidfeed.kid_feed_app.dto.response.ChildResponse;
import com.kidfeed.kid_feed_app.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping
    public ResponseEntity<ChildResponse> createChild(
            @PathVariable Long userId,
            @RequestBody ChildCreateRequest request) {
        ChildResponse response = childService.createChild(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<ChildResponse> getChildById(
            @PathVariable Long userId,
            @PathVariable Long childId) {
        ChildResponse response = childService.getChildById(userId, childId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChildResponse>> getChildrenByUser(
            @PathVariable Long userId) {
        List<ChildResponse> response = childService.getChildrenByUser(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{childId}")
    public ResponseEntity<ChildResponse> updateChild(
            @PathVariable Long userId,
            @PathVariable Long childId,
            @RequestBody ChildUpdateRequest request) {
        ChildResponse response = childService.updateChild(userId, childId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{childId}")
    public ResponseEntity<Void> deleteChild(
            @PathVariable Long userId,
            @PathVariable Long childId) {
        childService.deleteChild(userId, childId);
        return ResponseEntity.noContent().build();
    }
}
