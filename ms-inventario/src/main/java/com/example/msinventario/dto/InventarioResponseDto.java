package com.example.msinventario.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InventarioResponseDto {
    private Long id;
    private String AlmacenNombre;
    private Long productoId;
    private Integer cantidadDisponible;
    private LocalDateTime fechaActualizacion;
    private String productoNombre;
}
