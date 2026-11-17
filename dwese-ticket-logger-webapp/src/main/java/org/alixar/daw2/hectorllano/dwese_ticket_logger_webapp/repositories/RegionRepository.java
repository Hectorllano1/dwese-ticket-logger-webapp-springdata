package org.alixar.daw2.hectorllano.dwese_ticket_logger_webapp.repositories;

import org.alixar.daw2.hectorllano.dwese_ticket_logger_webapp.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Contrato con todas las operaciones disponibles sobre la tabla regions.
 * Al extender JpaRepository<Region, Long> ya disponemos de findAll(),
 * findById(), save(), deleteById()... sin escribir ninguna implementación:
 * Spring Data la genera en tiempo de ejecución.
 */
public interface RegionRepository extends JpaRepository<Region, Long> {

    // Consulta JPQL: ¿existe otra región con el mismo código (ignorando mayúsculas) y distinto id?
    @Query("SELECT COUNT(r) > 0 FROM Region r WHERE UPPER(r.code) = UPPER(:code) AND r.id != :id")
    boolean existsRegionByCodeAndNotId(@Param("code") String code, @Param("id") Long id);

    // Consulta JPQL: ¿existe alguna región con ese código (ignorando mayúsculas)?
    @Query("SELECT COUNT(r) > 0 FROM Region r WHERE UPPER(r.code) = UPPER(:code)")
    boolean existsRegionByCode(@Param("code") String code);
}
