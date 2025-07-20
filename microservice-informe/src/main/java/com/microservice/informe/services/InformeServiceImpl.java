package com.microservice.informe.services;

import com.microservice.informe.client.CompraClient;
import com.microservice.informe.client.VentaClient;
import com.microservice.informe.entities.Informe;
import com.microservice.informe.dto.CompraDTO;
import com.microservice.informe.dto.VentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class InformeServiceImpl implements IInformeService{

    private final CompraClient comprasClient;
    private final VentaClient ventasClient;

    public InformeServiceImpl(CompraClient comprasClient, VentaClient ventasClient) {
        this.comprasClient = comprasClient;
        this.ventasClient = ventasClient;
    }
    @Override
    public Informe generarInformeData() {
        LocalDate hoy = LocalDate.now();

        List<CompraDTO> compras = comprasClient.getAll().stream()
                .filter(c -> c.getFecha().isEqual(hoy))
                .toList();

        List<VentaDTO> ventas = ventasClient.getAll().stream()
                .filter(v -> v.getSaleDate().isEqual(hoy))
                .toList();

        double montoTotalCompras = compras.stream().mapToDouble(CompraDTO::getTotal).sum();
        double montoTotalVentas = ventas.stream().mapToDouble(VentaDTO::getCost).sum();

        Informe data = new Informe();
        data.setTotalCompras(compras.size());
        data.setMontoCompras(montoTotalCompras);
        data.setTotalVentas(ventas.size());
        data.setMontoVentas(montoTotalVentas);
        data.setBalance(montoTotalVentas - montoTotalCompras);
        data.setFechaInforme(hoy);
        data.setCompras(compras);
        data.setVentas(ventas);

        return data;
    }
}
