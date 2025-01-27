package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Peregrino;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.PeregrinoRepositorio;

@Service
public class PeregrinoServicio {

	@Autowired
	private PeregrinoRepositorio peregrinoRep;
	
	public Peregrino guardar(Peregrino entity) {
		return peregrinoRep.save(entity);
	}
	
	public Peregrino actualizar(Peregrino entity) {
		return peregrinoRep.save(entity);
	}
	
	public void borrar(Peregrino entity ) {
		peregrinoRep.delete(entity);
	}
	
	public void borrarPorId(Long id) {
		peregrinoRep.deleteById(id);
	}
	
	public void borrarPorLote(List<Peregrino> peregrinos) {
		peregrinoRep.deleteAll(peregrinos);
	}
	
	public Peregrino encontrarPorId(Long id) {
		return peregrinoRep.findById(id).get();
	}
	
	public List<Peregrino> encontrarTodos() {
		return peregrinoRep.findAll();
	}
}