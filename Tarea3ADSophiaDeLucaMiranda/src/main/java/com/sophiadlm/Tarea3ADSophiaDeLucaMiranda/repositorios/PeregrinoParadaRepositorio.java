package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Peregrino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeregrinoParadaRepositorio extends JpaRepository<Peregrino, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO peregrino_parada (id_peregrino, id_parada) VALUES (:idPeregrino, :idParada)", nativeQuery = true)
    void insertarPeregrinoParada(@Param("idPeregrino") Long idPeregrino, @Param("idParada") Long idParada);

    @Query(value = "SELECT id_parada FROM peregrino_parada WHERE id_peregrino = :idPeregrino", nativeQuery = true)
    List<Long> encontrarParadasPeregrino(@Param("idPeregrino") Long idPeregrino);
}