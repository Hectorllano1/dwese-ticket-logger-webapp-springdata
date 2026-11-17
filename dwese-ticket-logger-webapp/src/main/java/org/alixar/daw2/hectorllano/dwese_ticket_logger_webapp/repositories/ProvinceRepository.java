package org.alixar.daw2.hectorllano.dwese_ticket_logger_webapp.repositories;

import org.alixar.daw2.hectorllano.dwese_ticket_logger_webapp.entities.Province;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de provincias. findById(), save(), deleteById()... vienen de JpaRepository.
 */
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    // Sobrescribimos findAll() para cargar la región junto a cada provincia (evita cargas perezosas
    // al pintar province.region.name en la vista). No hace falta escribir ninguna consulta.
    @Override
    @EntityGraph(attributePaths = "region")
    List<Province> findAll();

    // ¿Existe alguna provincia con ese código (ignorando mayúsculas)?
    @Query("SELECT COUNT(p) > 0 FROM Province p WHERE UPPER(p.code) = UPPER(:code)")
    boolean existsProvinceByCode(@Param("code") String code);

    // ¿Existe otra provincia con ese código y distinto id?
    @Query("SELECT COUNT(p) > 0 FROM Province p WHERE UPPER(p.code) = UPPER(:code) AND p.id != :id")
    boolean existsProvinceByCodeAndNotId(@Param("code") String code, @Param("id") Long id);
}
