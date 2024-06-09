package com.example.msinventario.service;

import com.example.msinventario.dto.InventarioResponseDto;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.dto.ProductoDto;

import java.util.List;

public interface InventarioService {
    List<InventarioResponseDto> listar();
    Inventario guardar(Inventario inventario);
    InventarioResponseDto buscarPorId(Long id);
    Inventario buscarPorProductoId(Long productoId);
    Inventario editar(Inventario inventario);
    void eliminar(Long id);
    ProductoDto buscarProductoPorId(Integer id);
}
