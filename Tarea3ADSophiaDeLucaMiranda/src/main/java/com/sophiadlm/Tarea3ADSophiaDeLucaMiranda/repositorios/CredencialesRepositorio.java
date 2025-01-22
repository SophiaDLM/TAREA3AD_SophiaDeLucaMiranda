package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Credenciales;

@Repository
public interface CredencialesRepositorio extends JpaRepository<Credenciales, Long> {
	//Agregar métodos si se considera necesario una vez hechas las clases para arrancar javafx
}
