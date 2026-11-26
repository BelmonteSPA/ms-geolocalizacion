package com.belmonte.ms_geolocalizacion.service;

import com.belmonte.ms_geolocalizacion.dto.ActualizacionUbicacionDTO;
import com.belmonte.ms_geolocalizacion.dto.ConductorCercanoResponseDTO;
import com.belmonte.ms_geolocalizacion.modelo.UbicacionConductor;
import com.belmonte.ms_geolocalizacion.repository.UbicacionConductorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionConductorRepository ubicacionRepository;

    // Radio de la Tierra en kilómetros para la fórmula de Haversine
    private static final double RADIO_TIERRA_KM = 6371.0;

    public void actualizarUbicacion(ActualizacionUbicacionDTO dto) {
        UbicacionConductor ubicacion = ubicacionRepository.findByIdConductor(dto.getIdConductor())
                .orElseGet(() -> UbicacionConductor.builder()
                        .idConductor(dto.getIdConductor())
                        .build());

        ubicacion.setLatitud(dto.getLatitud());
        ubicacion.setLongitud(dto.getLongitud());
        ubicacion.setDisponible(dto.getDisponible() != null ? dto.getDisponible() : true);
        ubicacion.setFechaActual(LocalDate.from(LocalDateTime.now()));

        ubicacionRepository.save(ubicacion);
    }
    public List<ConductorCercanoResponseDTO> buscarConductoresCercanos(Double latitudPasajero, Double longitudPasajero, Double radioEnKm) {
        List<UbicacionConductor> todosLosConductores = ubicacionRepository.findAll();
        List<ConductorCercanoResponseDTO> conductoresCercanos = new ArrayList<>();

        for (UbicacionConductor conductor : todosLosConductores) {
            if (Boolean.TRUE.equals(conductor.getDisponible())) {
                double distancia = calcularDistanciaHaversine(
                        latitudPasajero, longitudPasajero,
                        conductor.getLatitud(), conductor.getLongitud()
                );

                if (distancia <= radioEnKm) {
                    conductoresCercanos.add(new ConductorCercanoResponseDTO(
                            conductor.getIdConductor(),
                            conductor.getLatitud(),
                            conductor.getLongitud(),
                            Math.round(distancia * 100.0) / 100.0
                    ));
                }
            }
        }

        return conductoresCercanos;
    }
    private double calcularDistanciaHaversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA_KM * c;
    }
}
