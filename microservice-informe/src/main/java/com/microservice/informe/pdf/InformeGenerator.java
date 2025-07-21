package com.microservice.informe.pdf;

import com.microservice.informe.dto.CompraDTO;
import com.microservice.informe.dto.VentaDTO;
import com.microservice.informe.entities.Informe;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class InformeGenerator {

    public static byte[] generatePdf(Informe informe) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Formato de fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Título
            writeLine(contentStream, "Informe Diario de Compras y Ventas", 50, 750, PDType1Font.HELVETICA_BOLD, 14);
            writeLine(contentStream, "Fecha: " + informe.getFechaInforme().format(formatter), 50, 730, PDType1Font.HELVETICA, 12);
            writeLine(contentStream, "========================================", 50, 715, PDType1Font.HELVETICA, 12);

            // Totales
            int y = 700;
            y = writeLine(contentStream, "Total de Compras: " + informe.getTotalCompras(), 50, y, PDType1Font.HELVETICA, 12);
            y = writeLine(contentStream, "Monto Total de Compras: $" + formatDouble(informe.getMontoCompras()), 50, y, PDType1Font.HELVETICA, 12);
            y = writeLine(contentStream, "Total de Ventas: " + informe.getTotalVentas(), 50, y, PDType1Font.HELVETICA, 12);
            y = writeLine(contentStream, "Monto Total de Ventas: $" + formatDouble(informe.getMontoVentas()), 50, y, PDType1Font.HELVETICA, 12);
            y = writeLine(contentStream, "Balance: $" + formatDouble(informe.getBalance()), 50, y, PDType1Font.HELVETICA_BOLD, 12);

            // Compras
            y = writeSectionTitle(contentStream, "Compras del día", 50, y - 15);
            for (CompraDTO compra : informe.getCompras()) {
                y = writeLine(contentStream,
                        "ID: " + compra.getId() +
                                " | Fecha: " + compra.getFecha().format(formatter) +
                                " | Monto: $" + formatDouble(compra.getTotal()),
                        50, y, PDType1Font.HELVETICA, 12);
                if (y < 50) break; // Si no hay espacio, detener
            }

            // Ventas
            y = writeSectionTitle(contentStream, "Ventas del día", 50, y - 15);
            for (VentaDTO venta : informe.getVentas()) {
                y = writeLine(contentStream,
                        "ID: " + venta.getId() +
                                " | Fecha: " + venta.getSaleDate().format(formatter) +
                                " | Costo: $" + formatDouble(venta.getCost()),
                        50, y, PDType1Font.HELVETICA, 12);
                if (y < 50) break;
            }

            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    private static int writeLine(PDPageContentStream cs, String text, float x, int y, PDType1Font font, int size) throws IOException {
        cs.setFont(font, size);
        cs.beginText();
        cs.newLineAtOffset(x, y);
        cs.showText(text);
        cs.endText();
        return y - 15;
    }

    private static int writeSectionTitle(PDPageContentStream cs, String title, float x, int y) throws IOException {
        return writeLine(cs, title, x, y, PDType1Font.HELVETICA_BOLD, 13);
    }

    private static double formatDouble(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
