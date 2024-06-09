package com.example.msinventario.service.impl;

import com.example.msinventario.dto.InventarioResponseDto;
import com.example.msinventario.dto.ProductoDto;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.feing.ProductoFeing;
import com.example.msinventario.repository.InventarioRepository;
import com.example.msinventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoFeing productoFeing;

    @Override
    public List<InventarioResponseDto> listar() {
        return inventarioRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public InventarioResponseDto buscarPorId(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElse(null);
        return inventario != null ? mapToDto(inventario) : null;
    }

    @Override
    public Inventario buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    @Override
    public Inventario editar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    @Override
    public ProductoDto buscarProductoPorId(Integer id) {
        return productoFeing.buscarPorId(id).getBody();
    }

    private InventarioResponseDto mapToDto(Inventario inventario) {
        InventarioResponseDto dto = new InventarioResponseDto();
        dto.setId(inventario.getId());
        dto.setAlmacenNombre(inventario.getAlmacenNombre());  // Asegúrate de que AlmacenNombre sea de tipo String
        dto.setProductoId(inventario.getProductoId());
        dto.setCantidadDisponible(inventario.getCantidadDisponible());
        dto.setFechaActualizacion(inventario.getFechaActualizacion());

        ProductoDto productoDto = buscarProductoPorId(inventario.getProductoId().intValue());
        if (productoDto != null) {
            dto.setProductoNombre(productoDto.getNombre());
        }

        return dto;
    }
}
