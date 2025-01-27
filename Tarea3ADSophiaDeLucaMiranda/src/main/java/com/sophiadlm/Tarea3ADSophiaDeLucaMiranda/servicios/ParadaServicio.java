package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Parada;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.ParadaRepositorio;

@Service
public class ParadaServicio {

	@Autowired
	private ParadaRepositorio paradaRep;
	
	public Parada guardar(Parada entity) {
		return paradaRep.save(entity);
	}
	
	public Parada actualizar(Parada entity) {
		return paradaRep.save(entity);
	}
	
	public void borrar(Parada entity ) {
		paradaRep.delete(entity);
	}
	
	public void borrarPorId(Long id) {
		paradaRep.deleteById(id);
	}
	
	public void borrarPorLote(List<Parada> paradas) {
		paradaRep.deleteAll(paradas);
	}
	
	public Parada encontrarPorId(Long id) {
		return paradaRep.findById(id).get();
	}
	
	public List<Parada> encontrarTodos() {
		return paradaRep.findAll();
	}
}