package com.example.msinventario.controller;

import com.example.msinventario.dto.ProductoDto;
import com.example.msinventario.entity.Inventario;
import com.example.msinventario.service.InventarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/{id}")
    @CircuitBreaker(name = "buscarPorIdCB", fallbackMethod = "buscarPorIdFallback")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        Inventario inventario = inventarioService.buscarPorId(id);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.guardar(inventario));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProductoId(@PathVariable Long productoId) {
        Inventario inventario = inventarioService.buscarPorProductoId(productoId);
        if (inventario != null) {
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/producto-info/{productoId}")
    public ResponseEntity<ProductoDto> buscarProductoPorId(@PathVariable Integer productoId) {
        ProductoDto producto = inventarioService.buscarProductoPorId(productoId);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> editar(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return ResponseEntity.ok(inventarioService.editar(inventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Método de fallback para buscarPorId en caso de que el circuit breaker esté abierto o haya un error
    public ResponseEntity<Inventario> buscarPorIdFallback(Long id, Throwable throwable) {
        return ResponseEntity.notFound().build();
    }
}
