package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Parada")
public class Parada {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private String nombre;
	
	private char region;
	
	private String responsable;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Credenciales credenciales;
	
	//SIMILAR A PEREGRINO ESTANCIA
	@OneToMany(mappedBy = "parada", cascade = CascadeType.ALL)
	private List<Estancia> listaEstancias = new ArrayList<>();
	
	@ManyToMany(mappedBy = "listaParadas")
	private List<Peregrino> listaPeregrinos = new ArrayList<>();
	
	public Parada() {
		
	}
	
	public Parada(Long id, String nombre, char region, String responsable) {
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.responsable = responsable;
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

	public char getRegion() {
		return region;
	}

	public void setRegion(char region) {
		this.region = region;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	
	@Override
	public String toString() {
		return "Parada [id=" + id + ", nombre=" + nombre + ", region=" + region + ", responsable=" + responsable + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, region, responsable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && region == other.region
				&& Objects.equals(responsable, other.responsable);
	}
}