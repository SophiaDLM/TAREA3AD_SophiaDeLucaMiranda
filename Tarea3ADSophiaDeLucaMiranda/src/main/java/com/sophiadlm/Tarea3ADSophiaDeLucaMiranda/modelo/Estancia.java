package com.sophiadlm.Tarea3ADSophiaDeLucaMiranda.modelo;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Estancia")
public class Estancia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	private LocalDate fecha;
	
	private boolean vip = false;
	
	//RELACIÓN ENTRE PEREGRINO Y ESTANCIA - EXPLICAR LUEGO
	@ManyToOne
	@JoinColumn(name = "idPeregrino", nullable = false)
	private Peregrino peregrino;
	
	//RELACIÓN ENTRE PARADA Y ESTANCIA
	@ManyToOne
	@JoinColumn(name = "idParada", nullable = false)
	private Parada parada;
	
	public Estancia() {
		
	}
	
	public Estancia(LocalDate fecha, boolean vip) {
		this.fecha = fecha;
		this.vip = vip;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public Peregrino getPeregrino() {
		return peregrino;
	}

	public void setPeregrino(Peregrino peregrino) {
		this.peregrino = peregrino;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	@Override
	public String toString() {
		return "Estancia [id=" + id + ", fecha=" + fecha + ", vip=" + vip + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, id, vip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estancia other = (Estancia) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id) && vip == other.vip;
	}
}