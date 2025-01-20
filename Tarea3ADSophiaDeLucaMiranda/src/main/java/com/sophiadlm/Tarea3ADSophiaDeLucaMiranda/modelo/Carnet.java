package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carnet")
public class Carnet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private LocalDate fechaexp = LocalDate.now();
	
	private double distancia = 0.0;
	
	private int nvips = 0;
	
	
	//BREVE ANOTACIÓN: SE COLOCA DE ESTA MANERA PARA QUE EL ID DEL
	//CARNET COINCIDA CON EL DE PEREGRINO EN VEZ DE QUE EL CARNET
	//TENGA SU PROPIO ID, ASÍ, SI NO EXISTE EL PEREGRINO, TAMPOCO
	//EL CARNET - [EXPLICAR MEJOR AL HACER LA DOCUMENTACIÓN EN EL
	//CÓDIGO (JAVADOC)]
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Peregrino peregrino;
	
	//ESTABLECE LA RELACIÓN ENTRE CARNET Y PARADA UNIDIRECCIONAL
	@OneToOne
	@JoinColumn(name = "idParadaInicial", nullable = false)
	private Parada paradaInicial;
	
	public Carnet() {
		
	}
	
	public Carnet(LocalDate fechaexp, double distancia, int nvips) {
		this.fechaexp = fechaexp;
		this.distancia = distancia;
		this.nvips = nvips;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaexp() {
		return fechaexp;
	}

	public void setFechaexp(LocalDate fechaexp) {
		this.fechaexp = fechaexp;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public int getNvips() {
		return nvips;
	}

	public void setNvips(int nvips) {
		this.nvips = nvips;
	}

	
	@Override
	public String toString() {
		return "Carnet [id=" + id + ", fechaexp=" + fechaexp + ", distancia=" + distancia + ", nvips=" + nvips + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(distancia, fechaexp, id, nvips);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carnet other = (Carnet) obj;
		return Double.doubleToLongBits(distancia) == Double.doubleToLongBits(other.distancia)
				&& Objects.equals(fechaexp, other.fechaexp) && Objects.equals(id, other.id) && nvips == other.nvips;
	}
}