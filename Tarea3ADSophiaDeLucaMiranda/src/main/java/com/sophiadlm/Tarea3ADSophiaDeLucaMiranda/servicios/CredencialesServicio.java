package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Credenciales;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.CredencialesRepositorio;

@Service
public class CredencialesServicio {
	
	@Autowired
	private CredencialesRepositorio credencialesRep;
	
	public Credenciales guardar(Credenciales entity) {
		return credencialesRep.save(entity);
	}
	
	public Credenciales actualizar(Credenciales entity) {
		return credencialesRep.save(entity);
	}
	
	public void borrar(Credenciales entity ) {
		credencialesRep.delete(entity);
	}
	
	public void borrarPorId(Long id) {
		credencialesRep.deleteById(id);
	}
	
	public void borrarPorLote(List<Credenciales> credenciales) {
		credencialesRep.deleteAll(credenciales);
	}
	
	public Credenciales encontrarPorId(Long id) {
		return credencialesRep.findById(id).get();
	}
	
	public List<Credenciales> encontrarTodos() {
		return credencialesRep.findAll();
	}
}