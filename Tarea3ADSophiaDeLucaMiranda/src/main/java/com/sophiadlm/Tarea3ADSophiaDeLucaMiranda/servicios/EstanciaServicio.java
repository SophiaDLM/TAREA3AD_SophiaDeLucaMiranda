package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Estancia;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.EstanciaRepositorio;

@Service
public class EstanciaServicio {
	
	@Autowired
	private EstanciaRepositorio estanciaRep;
	
	public Estancia guardar(Estancia entity) {
		return estanciaRep.save(entity);
	}
	
	public Estancia actualizar(Estancia entity) {
		return estanciaRep.save(entity);
	}
	
	public void borrar(Estancia entity ) {
		estanciaRep.delete(entity);
	}
	
	public void borrarPorId(Long id) {
		estanciaRep.deleteById(id);
	}
	
	public void borrarPorLote(List<Estancia> estancias) {
		estanciaRep.deleteAll(estancias);
	}
	
	public Estancia encontrarPorId(Long id) {
		return estanciaRep.findById(id).get();
	}
	
	public List<Estancia> encontrarTodos() {
		return estanciaRep.findAll();
	}

	///

	public List<Estancia> encontrarPorIdPeregrino(Long idPeregrino) {
		return estanciaRep.findByPeregrinoId(idPeregrino);
	}
}