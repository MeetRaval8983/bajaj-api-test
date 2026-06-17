package com.example.bfhl.controller;

import com.example.bfhl.dto.RequestDto;
import com.example.bfhl.dto.ResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<ResponseDto> processRequest(
            @RequestHeader(value = "X-Request-Id", defaultValue = "UNKNOWN") String requestId,
            @RequestBody RequestDto request) {
        
        ResponseDto response = bfhlService.processData(request, requestId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is up and running smoothly.");
    }
}