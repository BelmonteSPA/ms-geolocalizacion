package com.belmonte.ms_geolocalizacion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConductorDTO {

    @NotNull(message = "El ID del conductor no puede ser nulo")
    private Long idConductor;

    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean cuentaActiva;
}
