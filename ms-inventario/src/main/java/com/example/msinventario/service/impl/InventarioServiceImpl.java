package com.example.msinventario.service.impl;

import com.example.msinventario.dto.ProductoDto;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.feing.ProductoFeing;
import com.example.msinventario.repository.InventarioRepository;
import com.example.msinventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoFeing productoFeing;

    @Override
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario buscarPorId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
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
}
