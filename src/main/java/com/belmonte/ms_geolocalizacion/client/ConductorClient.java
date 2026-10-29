package com.belmonte.ms_geolocalizacion.client;

import com.belmonte.ms_geolocalizacion.dto.ConductorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-conductores", url = "${app.feign.conductores-url:http://localhost:8081}")
public interface ConductorClient {

    @GetMapping("/api/v1/conductores/{id}")
    ConductorDTO obtenerConductorPorId(@PathVariable("id") Long id);
}
