package com.example.msinventario.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String AlmacenNombre;  // Asegúrate de que sea de tipo String
    private Long productoId;
    private Integer cantidadDisponible;
    private LocalDateTime fechaActualizacion;
}
