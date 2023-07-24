package com.sii.aspirantes.aspirantes.helper;

import com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaDTO;
import com.sii.aspirantes.aspirantes.dto.aspirante.FichaPorPreparatoriaDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {
    public static String[] HEADERS_CARRERA = {
            "No. Solicitud",
            "No. Ficha",
            "Nombre",
            "Apellido Paterno",
            "Apellido Materno",
            "Aula",
            "Carrera",
            "CURP",
            "Teléfono",
            "Correo Electrónico"
    };

    public static String[] HEADERS_PREPAS = {
            "No. Solicitud",
            "No. Ficha",
            "Nombre",
            "Apellido Paterno",
            "Apellido Materno",
            "Carrera",
            "CURP",
            "Correo Electrónico",
            "Teléfono",
            "Género",
            "Escuela de procedencia",
            "Promedio general",
            "Municipio de preparatoria"
    };

    public static String SHEET_NAME = "concentrado_fichas";

    public static ByteArrayInputStream dataToExcel(List<AspiranteFichaDTO> fichas) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //create sheet
        Sheet sheet = workbook.createSheet(SHEET_NAME);

        //create row
        Row row = sheet.createRow(0);
        final var style = workbook.createCellStyle();
        final var font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for (int i = 0; i < HEADERS_CARRERA.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(HEADERS_CARRERA[i]);
        }

        //value rows
        int rowIndex = 1;
        for (AspiranteFichaDTO ficha : fichas) {

            Row datarow = sheet.createRow(rowIndex);

            rowIndex++;

            datarow.createCell(0).setCellValue(ficha.getNoSolicitud());
            datarow.createCell(1).setCellValue(ficha.getNoFicha());
            datarow.createCell(2).setCellValue(ficha.getNombreAspirante());
            datarow.createCell(3).setCellValue(ficha.getApellidoPaterno());
            datarow.createCell(4).setCellValue(ficha.getApellidoMaterno());
            datarow.createCell(5).setCellValue(ficha.getAula());
            datarow.createCell(6).setCellValue(ficha.getCarrera().trim());
            datarow.createCell(7).setCellValue(ficha.getCurp());
            datarow.createCell(8).setCellValue(ficha.getTelefono());
            datarow.createCell(9).setCellValue(ficha.getCorreoElectronico());
        }

        //Adapta el tamaño de la celda al contenido
        for (int i = 0; i < HEADERS_CARRERA.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);

        workbook.close();
        out.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream dataToExcelEscuelasProcedencia(List<FichaPorPreparatoriaDTO> fichas) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //create sheet
        Sheet sheet = workbook.createSheet(SHEET_NAME);

        //create row
        Row row = sheet.createRow(0);
        final var style = workbook.createCellStyle();
        final var font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for (int i = 0; i < HEADERS_PREPAS.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(HEADERS_PREPAS[i]);
        }

        //value rows
        int rowIndex = 1;
        for (FichaPorPreparatoriaDTO ficha : fichas) {

            Row datarow = sheet.createRow(rowIndex);

            rowIndex++;

            datarow.createCell(0).setCellValue(ficha.getNoSolicitud());
            datarow.createCell(1).setCellValue(ficha.getNoRecibo());
            datarow.createCell(2).setCellValue(ficha.getNombreAspirante());
            datarow.createCell(3).setCellValue(ficha.getApellidoPaterno());
            datarow.createCell(4).setCellValue(ficha.getApellidoMaterno());
            datarow.createCell(5).setCellValue(ficha.getNombreCarrera().trim());
            datarow.createCell(6).setCellValue(ficha.getCurp());
            datarow.createCell(7).setCellValue(ficha.getCorreoElectronico());
            datarow.createCell(8).setCellValue(ficha.getTelefono());
            datarow.createCell(9).setCellValue(ficha.getGenero());
            datarow.createCell(10).setCellValue(ficha.getNombreEscuelaProcedencia().trim());
            datarow.createCell(11).setCellValue(ficha.getPromedioGeneral());
            datarow.createCell(12).setCellValue(ficha.getMunicipio());
        }

        //Adapta el tamaño de la celda al contenido
        for (int i = 0; i < HEADERS_PREPAS.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);

        workbook.close();
        out.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
