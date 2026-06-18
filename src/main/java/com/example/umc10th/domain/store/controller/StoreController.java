package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController implements StoreControllerDocs {

    private final StoreService storeService;
}
