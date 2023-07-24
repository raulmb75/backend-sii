package com.sii.aspirantes.aspirantes.Service;

import com.sii.aspirantes.aspirantes.Repository.AspiranteRepository;
import com.sii.aspirantes.aspirantes.dto.aspirante.AspiranteFichaDTO;
import com.sii.aspirantes.aspirantes.dto.aspirante.FichaPorPreparatoriaDTO;
import com.sii.aspirantes.aspirantes.helper.Helper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    private final AspiranteRepository aspiranteRepository;

    public ExcelService(AspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }

    public ByteArrayInputStream getActualData(String periodo) throws IOException {
        List<AspiranteFichaDTO> listaFichas = aspiranteRepository.obtenerFichaAspirantesTodasCarrerasAula(Integer.parseInt(periodo));
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(listaFichas);
        return byteArrayInputStream;
    }

    public ByteArrayInputStream getActualDataByCarrera(String periodo, Integer reticula) throws IOException {
        List<AspiranteFichaDTO> listaFichas = aspiranteRepository.obtenerFichaAspirantesPorCarreraAulaV2(Integer.parseInt(periodo), reticula);
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(listaFichas);
        return byteArrayInputStream;
    }

    public ByteArrayInputStream fichasPorPreparatoriasProcedenciaEstado(String periodo, Integer estado) throws IOException {
        List<FichaPorPreparatoriaDTO> listaFichas = aspiranteRepository.buscarFichasPorPreparatoriaProcedenciaEstado(Integer.parseInt(periodo), estado);
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcelEscuelasProcedencia(listaFichas);
        return byteArrayInputStream;
    }

    public ByteArrayInputStream fichasPorPreparatoriasProcedenciaMunicipio(String periodo,
                                                                        Integer estado,
                                                                        String municipio) throws IOException {
        List<FichaPorPreparatoriaDTO> listaFichas = aspiranteRepository.buscarFichasPorPreparatoriaProcedenciaMunicipio(Integer.parseInt(periodo), estado, municipio);
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcelEscuelasProcedencia(listaFichas);
        return byteArrayInputStream;
    }

    public ByteArrayInputStream fichasPorNombrePreparatoriaProedencia(String periodo,
                                                                      Integer estado,
                                                                      String municipio,
                                                                      String clavePreparatoria) throws IOException {
        List<FichaPorPreparatoriaDTO> listaFichas = aspiranteRepository.buscarFichasPorNombrePreparatoriaProcedencia(Integer.parseInt(periodo), estado, municipio, clavePreparatoria);
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcelEscuelasProcedencia(listaFichas);
        return byteArrayInputStream;
    }

}
