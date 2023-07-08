package com.daemontech.sgct_mobile.modelos.entityKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
public class Enfermedad_R_Ternera_R_Variante_PK {


    private Enfermedad_R_Variante_PK enfermedad_r_variante_pk;

    private Long id_Ternera;

    private LocalDate fec_inic_enf;

	public Enfermedad_R_Variante_PK getEnfermedad_r_variante_pk() {
		return enfermedad_r_variante_pk;
	}

	public void setEnfermedad_r_variante_pk(Enfermedad_R_Variante_PK enfermedad_r_variante_pk) {
		this.enfermedad_r_variante_pk = enfermedad_r_variante_pk;
	}

	public Long getId_Ternera() {
		return id_Ternera;
	}

	public void setId_Ternera(Long id_Ternera) {
		this.id_Ternera = id_Ternera;
	}

	public LocalDate getFec_inic_enf() {
		return fec_inic_enf;
	}

	public void setFec_inic_enf(LocalDate fec_inic_enf) {
		this.fec_inic_enf = fec_inic_enf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enfermedad_r_variante_pk, fec_inic_enf, id_Ternera);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermedad_R_Ternera_R_Variante_PK other = (Enfermedad_R_Ternera_R_Variante_PK) obj;
		return Objects.equals(enfermedad_r_variante_pk, other.enfermedad_r_variante_pk)
				&& Objects.equals(fec_inic_enf, other.fec_inic_enf) && Objects.equals(id_Ternera, other.id_Ternera);
	}

	@Override
	public String toString() {
		return "PK";
	}
	
	
    
    


}