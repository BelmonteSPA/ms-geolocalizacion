package com.belmonte.ms_geolocalizacion.repository;

import com.belmonte.ms_geolocalizacion.modelo.UbicacionConductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionConductorRepository extends JpaRepository<UbicacionConductor, Long> {

    Optional<UbicacionConductor> findByConductorId(Long idConductor);

}
