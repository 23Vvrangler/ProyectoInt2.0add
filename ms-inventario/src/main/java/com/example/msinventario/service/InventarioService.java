package com.example.msinventario.service;

import com.example.msinventario.dto.ProductoDto;
import com.example.msinventario.entity.Inventario;

import java.util.List;

public interface InventarioService {
    List<Inventario> listar();
    Inventario guardar(Inventario inventario);
    Inventario buscarPorId(Long id);
    Inventario buscarPorProductoId(Long productoId);
    Inventario editar(Inventario inventario);
    void eliminar(Long id);
    ProductoDto buscarProductoPorId(Integer id);

}