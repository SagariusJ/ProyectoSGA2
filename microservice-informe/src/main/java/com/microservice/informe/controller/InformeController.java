package com.microservice.informe.controller;

import com.microservice.informe.entities.Informe;
import com.microservice.informe.pdf.InformeGenerator;
import com.microservice.informe.services.IInformeService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/informe")
public class InformeController {

    private final IInformeService informeService;

    public InformeController(IInformeService informeService) {
        this.informeService = informeService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<ByteArrayResource> downloadDailyReport() throws IOException {
        Informe informe = informeService.generarInformeData();

        byte[] pdfBytes = InformeGenerator.generatePdf(informe);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "informe_diario.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .body(new ByteArrayResource(pdfBytes));
    }
}
