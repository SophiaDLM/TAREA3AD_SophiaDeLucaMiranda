package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.Carnet;
import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.repositorios.CarnetRepositorio;

@Service
public class CarnetServicio {
	
	@Autowired
	private CarnetRepositorio carnetRep;
	
	public Carnet guardar(Carnet entity) {
		return carnetRep.save(entity);
	}
	
	public Carnet actualizar(Carnet entity) {
		return carnetRep.save(entity);
	}
	
	public void borrar(Carnet entity ) {
		carnetRep.delete(entity);
	}
	
	public void borrarPorId(Long id) {
		carnetRep.deleteById(id);
	}
	
	public void borrarPorLote(List<Carnet> carnets) {
		carnetRep.deleteAll(carnets);
	}
	
	public Carnet encontrarPorId(Long id) {
		return carnetRep.findById(id).get();
	}
	
	public List<Carnet> encontrarTodos() {
		return carnetRep.findAll();
	}
}