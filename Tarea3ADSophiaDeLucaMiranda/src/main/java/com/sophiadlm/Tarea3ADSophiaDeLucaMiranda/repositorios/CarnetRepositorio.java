package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Carnet;

/***
 * Interfaz CarnetRepositorio que hereda de JpaRepository y se inyecta en CarnetServicio.
 */
@Repository
public interface CarnetRepositorio extends JpaRepository<Carnet, Long> {

}