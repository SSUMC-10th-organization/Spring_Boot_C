package com.example.umc10th.domain.region.controller;

import com.example.umc10th.domain.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regions")
public class RegionController implements RegionControllerDocs {

    private final RegionService regionService;
}
