package com.example.securityexercise.controller;

import com.example.securityexercise.dto.SalesReport;
import com.example.securityexercise.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/reports")
public class AdminController {

    private final ReportService reportService;

    public AdminController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public SalesReport getSalesReport() {
        return reportService.getSalesReport();
    }
}