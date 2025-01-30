package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Peregrino")
public class Peregrino {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private String nombre;
	
	private String nacionalidad;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Credenciales credenciales;
	
	//REPRESENTA LA RELACIÓN ENTRE PEREGRINO Y CARNET
	@OneToOne(mappedBy = "peregrino", cascade = CascadeType.ALL)
	private Carnet carnet;
	
	//REPRESENTA LA RELACIÓN ENTRE ESTANCIAS Y PEREGRINO - BIDIRECCIONAL
	@OneToMany(mappedBy = "peregrino", cascade = CascadeType.ALL)
	private List<Estancia> listaEstancias = new ArrayList<>();
	
	//REPRESENTA LA RELACIÓN ENTRE PARADA Y PEREGRINO
	//CREA NUEVA TABLA CON LOS IDS DE AMBAS TABLAS
	//MÁS TARDE ES POSIBLE QUE SE AÑADA UNA COLUMNA DE TIPO FECHA PARA
	//PERMITIR MÁS REGISTROS EN OTROS DÍAS
	@ManyToMany
	@JoinTable(name = "PeregrinoParada", joinColumns = @JoinColumn(name = "idPeregrino"), inverseJoinColumns = @JoinColumn(name = "idParada"))
	private List<Parada> listaParadas = new ArrayList<>();
	
	public Peregrino() {
		
	}
	
	public Peregrino(Long id, String nombre, String nacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

	public Credenciales getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(Credenciales credenciales) {
		this.credenciales = credenciales;
	}

	public List<Estancia> getListaEstancias() {
		return listaEstancias;
	}

	public void setListaEstancias(List<Estancia> listaEstancias) {
		this.listaEstancias = listaEstancias;
	}

	public List<Parada> getListaParadas() {
		return listaParadas;
	}

	public void setListaParadas(List<Parada> listaParadas) {
		this.listaParadas = listaParadas;
	}

	//EDITAR LUEGO
	@Override
	public String toString() {
		return "Peregrino [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(carnet, id, nacionalidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peregrino other = (Peregrino) obj;
		return Objects.equals(carnet, other.carnet) && Objects.equals(id, other.id)
				&& Objects.equals(nacionalidad, other.nacionalidad) && Objects.equals(nombre, other.nombre);
	}
}