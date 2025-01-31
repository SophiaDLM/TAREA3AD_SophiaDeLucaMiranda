package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.ParadaRepositorio;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.PeregrinoParadaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeregrinoParadaServicio {
    @Autowired
    private PeregrinoParadaRepositorio peregrinoParadaRepositorio;

    @Autowired
    private ParadaRepositorio paradaRepositorio;

    public void guardarPeregrinoParada(Long idPeregrino, Long idParada) {
        peregrinoParadaRepositorio.insertarPeregrinoParada(idPeregrino, idParada);
    }

    public List<Parada> obtenerParadaPeregrino(Long idPeregrino) {
        List<Long> idParadas = peregrinoParadaRepositorio.encontrarParadasPeregrino(idPeregrino);
        return paradaRepositorio.findAllById(idParadas);
    }
}