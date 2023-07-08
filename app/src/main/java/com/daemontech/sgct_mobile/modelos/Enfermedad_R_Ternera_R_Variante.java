package com.daemontech.sgct_mobile.modelos;

import com.daemontech.sgct_mobile.modelos.entityKey.Enfermedad_R_Ternera_R_Variante_PK;

import java.time.LocalDate;

public class Enfermedad_R_Ternera_R_Variante {


    private Enfermedad_R_Ternera_R_Variante_PK enfermedad_r_ternera_r_variante_pk;

    private LocalDate fc_fin_enfe;

    private String severidad_enfermedad;

    private String motivo_baja_enfermedad;

    private boolean eliminado;

    private Ternera terneraVT;
    private Enfermedad enfermedadVT;

    private Variante_Enfermedad varianteVT;

    public Enfermedad_R_Ternera_R_Variante_PK getEnfermedad_r_ternera_r_variante_pk() {
        return enfermedad_r_ternera_r_variante_pk;
    }

    public void setEnfermedad_r_ternera_r_variante_pk(
            Enfermedad_R_Ternera_R_Variante_PK enfermedad_r_ternera_r_variante_pk) {
        this.enfermedad_r_ternera_r_variante_pk = enfermedad_r_ternera_r_variante_pk;
    }

    public LocalDate getFc_fin_enfe() {
        return fc_fin_enfe;
    }

    public void setFc_fin_enfe(LocalDate fc_fin_enfe) {
        this.fc_fin_enfe = fc_fin_enfe;
    }

    public String getSeveridad_enfermedad() {
        return severidad_enfermedad;
    }

    public void setSeveridad_enfermedad(String severidad_enfermedad) {
        this.severidad_enfermedad = severidad_enfermedad;
    }

    public String getMotivo_baja_enfermedad() {
        return motivo_baja_enfermedad;
    }

    public void setMotivo_baja_enfermedad(String motivo_baja_enfermedad) {
        this.motivo_baja_enfermedad = motivo_baja_enfermedad;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Ternera getTerneraVT() {
        return terneraVT;
    }

    public void setTerneraVT(Ternera terneraVT) {
        this.terneraVT = terneraVT;
    }

    public Enfermedad getEnfermedadVT() {
        return enfermedadVT;
    }

    public void setEnfermedadVT(Enfermedad enfermedadVT) {
        this.enfermedadVT = enfermedadVT;
    }

    public Variante_Enfermedad getVarianteVT() {
        return varianteVT;
    }

    public void setVarianteVT(Variante_Enfermedad varianteVT) {
        this.varianteVT = varianteVT;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enfermedad_r_ternera_r_variante_pk != null ? enfermedad_r_ternera_r_variante_pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Enfermedad_R_Ternera_R_Variante)) {
            return false;
        }
        Enfermedad_R_Ternera_R_Variante other = (Enfermedad_R_Ternera_R_Variante) object;
        if ((this.enfermedad_r_ternera_r_variante_pk == null && other.enfermedad_r_ternera_r_variante_pk != null)
                || (this.enfermedad_r_ternera_r_variante_pk != null && !this.enfermedad_r_ternera_r_variante_pk.equals(other.enfermedad_r_ternera_r_variante_pk))) {
            return false;
        }
        return true;
    }

}
