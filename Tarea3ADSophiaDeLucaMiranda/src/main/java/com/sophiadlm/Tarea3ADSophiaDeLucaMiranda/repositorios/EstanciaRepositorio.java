package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Estancia;

import java.util.List;

@Repository
public interface EstanciaRepositorio extends JpaRepository<Estancia, Long> {
    List<Estancia> findByPeregrinoId(Long idPeregrino);
}