package com.exchange.andevisbackend.controller;

import com.exchange.andevisbackend.DTO.Record;
import com.exchange.andevisbackend.service.ConversionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ConversionRecordController {

    private final ConversionRecordService recordService;

    @Autowired
    public ConversionRecordController(ConversionRecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records/{userId}")
    public List<Record> getRecords(@PathVariable(name = "userId") Long userId){

        return recordService.findRecordsByUser(userId);
    }
}
