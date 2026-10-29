package com.belmonte.ms_geolocalizacion.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActualizacionUbicacionDTO {

    @NotNull(message = "El ID del conductor no puede ser nulo")
    private Long idConductor;

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "La latitud mínima válida es -90.0")
    @DecimalMax(value = "90.0", message = "La latitud máxima válida es 90.0")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "La longitud mínima válida es -180.0")
    @DecimalMax(value = "180.0", message = "La longitud máxima válida es 180.0")
    private Double longitud;

    private Boolean disponible;
    private LocalDate fechaActual;
}
