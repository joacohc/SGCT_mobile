package com.daemontech.sgct_mobile.modelos;

import com.daemontech.sgct_mobile.modelos.entityKey.Enfermedad_R_Variante_PK;

public class Enfermedad_R_Variante {
    private Enfermedad_R_Variante_PK enfermedad_r_variante_pk;
    private Variante_Enfermedad varianteV;
    private Enfermedad enfermedadV;

    public Enfermedad_R_Variante_PK getEnfermedad_r_variante_pk() {
        return enfermedad_r_variante_pk;
    }

    public void setEnfermedad_r_variante_pk(Enfermedad_R_Variante_PK enfermedad_r_variante_pk) {
        this.enfermedad_r_variante_pk = enfermedad_r_variante_pk;
    }

    public Variante_Enfermedad getVarianteV() {
        return varianteV;
    }

    public void setVarianteV(Variante_Enfermedad varianteV) {
        this.varianteV = varianteV;
    }

    public Enfermedad getEnfermedadV() {
        return enfermedadV;
    }

    public void setEnfermedadV(Enfermedad enfermedadV) {
        this.enfermedadV = enfermedadV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enfermedad_r_variante_pk != null ? enfermedad_r_variante_pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Enfermedad_R_Variante)) {
            return false;
        }
        Enfermedad_R_Variante other = (Enfermedad_R_Variante) object;
        if ((this.enfermedad_r_variante_pk == null && other.enfermedad_r_variante_pk != null)
                || (this.enfermedad_r_variante_pk != null && !this.enfermedad_r_variante_pk.equals(other.enfermedad_r_variante_pk))) {
            return false;
        }
        return true;
    }


}
