package com.daemontech.sgct_mobile.modelos;

import java.util.List;

public class Variante_Enfermedad {

    private Long id_Variante_Enfermedad;

    private String variante_enfermedad;

    private boolean eliminado;

    private List<Enfermedad_R_Variante> enfermedades_variantes;

    private List<Enfermedad_R_Ternera_R_Variante> enfermedades_terneras_variantes;

    public Long getId_Variante_Enfermedad() {
        return id_Variante_Enfermedad;
    }

    public void setId_Variante_Enfermedad(Long id_Variante_Enfermedad) {
        this.id_Variante_Enfermedad = id_Variante_Enfermedad;
    }

    public String getVariante_enfermedad() {
        return variante_enfermedad;
    }

    public void setVariante_enfermedad(String variante_enfermedad) {
        this.variante_enfermedad = variante_enfermedad;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<Enfermedad_R_Variante> getEnfermedades_variantes() {
        return enfermedades_variantes;
    }

    public void setEnfermedades_variantes(List<Enfermedad_R_Variante> enfermedades_variantes) {
        this.enfermedades_variantes = enfermedades_variantes;
    }

    public List<Enfermedad_R_Ternera_R_Variante> getEnfermedades_terneras_variantes() {
        return enfermedades_terneras_variantes;
    }

    public void setEnfermedades_terneras_variantes(
            List<Enfermedad_R_Ternera_R_Variante> enfermedades_terneras_variantes) {
        this.enfermedades_terneras_variantes = enfermedades_terneras_variantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_Variante_Enfermedad != null ? id_Variante_Enfermedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Variante_Enfermedad)) {
            return false;
        }
        Variante_Enfermedad other = (Variante_Enfermedad) object;
        if ((this.id_Variante_Enfermedad == null && other.id_Variante_Enfermedad != null)
                || (this.id_Variante_Enfermedad != null
                && !this.id_Variante_Enfermedad.equals(other.id_Variante_Enfermedad))) {
            return false;
        }
        return true;
    }

}
