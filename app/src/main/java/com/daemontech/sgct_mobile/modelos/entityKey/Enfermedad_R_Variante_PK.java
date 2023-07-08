package com.daemontech.sgct_mobile.modelos.entityKey;


import  java.io.Serializable;

import java.util.Objects;

public class Enfermedad_R_Variante_PK {

	private Long id_Enfermedad;

	private Long id_Variante_Enfermedad;

	public Enfermedad_R_Variante_PK(Long id_Enfermedad, Long id_Variante_Enfermedad) {
		this.id_Enfermedad = id_Enfermedad;
		this.id_Variante_Enfermedad=id_Variante_Enfermedad;
	}
	public Enfermedad_R_Variante_PK() {

	}
	public Long getId_Enfermedad() {
		return id_Enfermedad;
	}

	public void setId_Enfermedad(Long id_Enfermedad) {
		this.id_Enfermedad = id_Enfermedad;
	}

	public Long getId_Variante_Enfermedad() {
		return id_Variante_Enfermedad;
	}

	public void setId_Variante_Enfermedad(Long id_Variante_Enfermedad) {
		this.id_Variante_Enfermedad = id_Variante_Enfermedad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_Enfermedad, id_Variante_Enfermedad);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermedad_R_Variante_PK other = (Enfermedad_R_Variante_PK) obj;
		return Objects.equals(id_Enfermedad, other.id_Enfermedad)
				&& Objects.equals(id_Variante_Enfermedad, other.id_Variante_Enfermedad);
	}
	
	
	
	
}
