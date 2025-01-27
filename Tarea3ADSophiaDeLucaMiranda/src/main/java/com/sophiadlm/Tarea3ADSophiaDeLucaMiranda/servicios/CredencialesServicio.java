package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.servicios;

import java.util.List;

import com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo.TipoUsuario;
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

	///

	public Credenciales encontrarPorNombreUsuario(String nombre) {
		return credencialesRep.findByNombreUsuario(nombre);
	}

	public TipoUsuario autenticar(String usuario, String contraseña) {
		Credenciales usu = this.encontrarPorNombreUsuario(usuario);

		if(usu == null) {
			return TipoUsuario.INVITADO;
		} else {
			if(contraseña.equals(usu.getContraseña()) && usu.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {
				return TipoUsuario.ADMINISTRADOR;

			} else if (contraseña.equals(usu.getContraseña()) && usu.getTipoUsuario().equals(TipoUsuario.PEREGRINO)) {
				return TipoUsuario.PEREGRINO;

			} else if (contraseña.equals(usu.getContraseña()) && usu.getTipoUsuario().equals(TipoUsuario.PARADA)) {
				return TipoUsuario.PARADA;

			} else {
				return TipoUsuario.INVITADO;
			}
		}
	}
}