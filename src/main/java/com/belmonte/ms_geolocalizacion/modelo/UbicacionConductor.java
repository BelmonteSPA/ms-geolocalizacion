package com.belmonte.ms_geolocalizacion.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ubicacion_conductor")
@Builder
public class UbicacionConductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacionConductor")
    private Long idUbicacion;
    @Column(name = "id_conductor", nullable = false, unique = true)
    private Long idConductor;
    @Column(nullable = false)
    private Double latitud;
    @Column(nullable = false)
    private Double longitud;
    @Column(nullable = false)
    private Boolean disponible;
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActual;
}
