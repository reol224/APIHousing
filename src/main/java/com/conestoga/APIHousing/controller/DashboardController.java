package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.service.EventService;
import com.conestoga.APIHousing.service.MaintenanceRequestService;
import com.conestoga.APIHousing.service.ResidenceService;
import com.conestoga.APIHousing.service.UnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {
    UnitService unitService;
    ResidenceService residenceService;
    MaintenanceRequestService maintenanceRequestService;
    EventService eventService;

    public DashboardController(UnitService unitService, ResidenceService residenceService, MaintenanceRequestService maintenanceRequestService, EventService eventService) {
        this.unitService = unitService;
        this.residenceService = residenceService;
        this.maintenanceRequestService = maintenanceRequestService;
        this.eventService = eventService;
    }

    @GetMapping("/dashboard")
    public Map<String, Integer> getDashboard() {
        Map<String, Integer> map = new HashMap<>();
        map.put("units", unitService.getAllUnits().size());
        map.put("residences", residenceService.getAllResidences().size());
        map.put("maintenanceRequests", maintenanceRequestService.getAllMaintenanceRequests().size());
        map.put("events", eventService.getAllEvents().size());
        return map;
    }
}
