package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.MaintenanceRequestDTO;
import com.conestoga.APIHousing.dtos.TransactionDTO;
import com.conestoga.APIHousing.service.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {
    UnitService unitService;
    ResidenceService residenceService;
    MaintenanceRequestService maintenanceRequestService;
    EventService eventService;
    TransactionService transactionService;

    public DashboardController(UnitService unitService, ResidenceService residenceService, MaintenanceRequestService maintenanceRequestService, EventService eventService,
                               TransactionService transactionService) {
        this.unitService = unitService;
        this.residenceService = residenceService;
        this.maintenanceRequestService = maintenanceRequestService;
        this.eventService = eventService;
        this.transactionService = transactionService;
    }

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        Map<String, Object> map = new HashMap<>();
        map.put("Units", unitService.getAllUnits().size());
        map.put("Residences", residenceService.getAllResidences().size());

        // Maintenance requests
        int totalMaintenanceRequests = maintenanceRequestService.getAllMaintenanceRequests().size();
        int solvedMaintenanceRequests = 0;

        for (MaintenanceRequestDTO maintenanceRequestDTO : maintenanceRequestService.getAllMaintenanceRequests()) {
            if (maintenanceRequestDTO.getRequestStatus() == 1) {
                solvedMaintenanceRequests++;
            }
        }

        map.put("Total Maintenance requests", totalMaintenanceRequests);
        map.put("Solved Maintenance requests", solvedMaintenanceRequests);

        double percentSolved = (double) solvedMaintenanceRequests / totalMaintenanceRequests * 100;
        map.put("Percent Solved", percentSolved);



        map.put("Events", eventService.getAllEvents().size());

        // Transactions
        BigDecimal sumOfTransactions = BigDecimal.valueOf(0);
        for (TransactionDTO transaction : transactionService.getAllTransactions()) {
            sumOfTransactions = sumOfTransactions.add(transaction.getAmount());
        }
        map.put("Transactions", sumOfTransactions.intValue());

        return map;
    }
}
