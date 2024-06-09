package com.example.msinventario.controller;

import com.example.msinventario.dto.InventarioResponseDto;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponseDto> crearInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.guardar(inventario);
        InventarioResponseDto responseDto = inventarioService.buscarPorId(nuevoInventario.getId());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDto>> listarInventarios() {
        List<InventarioResponseDto> inventarios = inventarioService.listar();
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDto> obtenerInventarioPorId(@PathVariable Long id) {
        InventarioResponseDto inventario = inventarioService.buscarPorId(id);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDto> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario inventarioActualizado = inventarioService.editar(inventario);
        InventarioResponseDto responseDto = inventarioService.buscarPorId(inventarioActualizado.getId());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
