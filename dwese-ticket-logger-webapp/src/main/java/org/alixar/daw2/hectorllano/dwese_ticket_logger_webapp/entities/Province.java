package org.alixar.daw2.hectorllano.dwese_ticket_logger_webapp.entities;

import jakarta.persistence.*; // Anotaciones de JPA
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase `Province` modela una provincia. Contiene `id`, `code`, `name` y `region`,
 * donde `region` es la relación con la entidad `Region` (la comunidad autónoma a la que
 * pertenece la provincia).
 */
@Entity // Marca esta clase como una entidad JPA.
@Table(name = "provinces") // Nombre de la tabla asociada a esta entidad.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    // Identificador único, autogenerado por la base de datos. Long, como indica el enunciado.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código de la provincia. Ejemplo: "23" para Jaén.
    @NotEmpty(message = "{msg.province.code.notEmpty}")
    @Size(max = 2, message = "{msg.province.code.size}")
    @Column(name = "code", nullable = false, length = 2)
    private String code;

    // Nombre completo de la provincia, como "Sevilla" o "Jaén".
    @NotEmpty(message = "{msg.province.name.notEmpty}")
    @Size(max = 100, message = "{msg.province.name.size}")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Relación muchas provincias -> una región (clave foránea id_region en la tabla provinces).
    @NotNull(message = "{msg.province.region.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;

    /**
     * Constructor sin `id`, para crear una provincia antes de guardarla en la base de datos.
     */
    public Province(String code, String name, Region region) {
        this.code = code;
        this.name = name;
        this.region = region;
    }
}
