package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Carnet")
public class Carnet {
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private LocalDate fechaexp;
	
	private double distancia;
	
	private int nvips;
	
	
	//BREVE ANOTACIÓN: SE COLOCA DE ESTA MANERA PARA QUE EL ID DEL
	//CARNET COINCIDA CON EL DE PEREGRINO EN VEZ DE QUE EL CARNET
	//TENGA SU PROPIO ID, ASÍ, SI NO EXISTE EL PEREGRINO, TAMPOCO
	//EL CARNET - [EXPLICAR MEJOR AL HACER LA DOCUMENTACIÓN EN EL
	//CÓDIGO (JAVADOC)]
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_carnet_peregrino"))
	private Peregrino peregrino;
	
	//ESTABLECE LA RELACIÓN ENTRE CARNET Y PARADA UNIDIRECCIONAL
	@OneToOne
	@JoinColumn(name = "idParadaInicial", nullable = false)
	private Parada paradaInicial;

	public Carnet() {

	}
	
	public Carnet(Long id) {
		this.id = id;
		this.fechaexp = LocalDate.now();
		this.distancia = 0.0;
		this.nvips = 0;
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

	public Parada getParadaInicial() {
		return paradaInicial;
	}

	public void setParadaInicial(Parada paradaInicial) {
		this.paradaInicial = paradaInicial;
	}

	public Peregrino getPeregrino() {
		return peregrino;
	}

	public void setPeregrino(Peregrino peregrino) {
		this.peregrino = peregrino;
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