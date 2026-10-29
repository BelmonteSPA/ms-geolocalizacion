package com.belmonte.ms_geolocalizacion.controller;

import com.belmonte.ms_geolocalizacion.dto.ActualizacionUbicacionDTO;
import com.belmonte.ms_geolocalizacion.dto.ConductorCercanoResponseDTO;
import com.belmonte.ms_geolocalizacion.service.UbicacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<Void> actualizarUbicacion(@Valid @RequestBody ActualizacionUbicacionDTO dto) {
        ubicacionService.actualizarUbicacion(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Endpoint para obtener una lista de conductores cercanos a un pasajero.
     */
    @GetMapping("/cercanos")
    public ResponseEntity<List<ConductorCercanoResponseDTO>> obtenerConductoresCercanos(
            @RequestParam Double latitud,
            @RequestParam Double longitud,
            @RequestParam(defaultValue = "5.0") Double radioKm) {

        List<ConductorCercanoResponseDTO> conductores = ubicacionService.buscarConductoresCercanos(latitud, longitud, radioKm);
        return ResponseEntity.ok(conductores);
    }
}
