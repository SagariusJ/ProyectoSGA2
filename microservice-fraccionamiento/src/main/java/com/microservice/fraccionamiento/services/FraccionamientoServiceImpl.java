package com.microservice.fraccionamiento.services;

import com.microservice.fraccionamiento.client.InventoryClient;
import com.microservice.fraccionamiento.dto.ProductDTO;
import com.microservice.fraccionamiento.dto.WarehouseDTO;
import com.microservice.fraccionamiento.entities.Fraccionamiento;
import com.microservice.fraccionamiento.persistence.FraccionamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraccionamientoServiceImpl implements IFraccionamientoService {

    @Autowired
    private FraccionamientoRepository fraccionamientoRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public Fraccionamiento save(Fraccionamiento frac) {
        // Verificar si el producto existe
        ProductDTO product = inventoryClient.getProductById(frac.getProductoId());
        if (product == null) {
            throw new RuntimeException("Producto con ID " + frac.getProductoId() + " no encontrado");
        }

        // Verificar sucursal origen
        WarehouseDTO origen = inventoryClient.getWarehouseById(frac.getSucursalOrigenId());
        if (origen == null) {
            throw new RuntimeException("Sucursal origen con ID " + frac.getSucursalOrigenId() + " no encontrada");
        }

        // Verificar sucursal destino
        WarehouseDTO destino = inventoryClient.getWarehouseById(frac.getSucursalDestinoId());
        if (destino == null) {
            throw new RuntimeException("Sucursal destino con ID " + frac.getSucursalDestinoId() + " no encontrada");
        }

        return fraccionamientoRepository.save(frac);
    }

    @Override
    public void deleteById(Long id) {
        fraccionamientoRepository.deleteById(id);
    }

    @Override
    public Fraccionamiento findById(Long id) {
        return fraccionamientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Fraccionamiento> findAll() {
        return (List<Fraccionamiento>) fraccionamientoRepository.findAll();
    }
}
