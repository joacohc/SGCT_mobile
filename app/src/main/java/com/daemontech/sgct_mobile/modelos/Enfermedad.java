package com.daemontech.sgct_mobile.modelos;

import java.util.List;

public class Enfermedad {

    private Long id_Enfermedad;

    private String enfermedad;

    private boolean eliminado;

    private List<Enfermedad_R_Variante> enfermedades_variantes;

    private List<Enfermedad_R_Ternera_R_Variante> enfermedades_terneras_variantes;

    public Long getId_Enfermedad() {
        return id_Enfermedad;
    }

    public void setId_Enfermedad(Long id_Enfermedad) {
        this.id_Enfermedad = id_Enfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
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

    public void setEnfermedades_terneras_variantes(List<Enfermedad_R_Ternera_R_Variante> enfermedades_terneras_variantes) {
        this.enfermedades_terneras_variantes = enfermedades_terneras_variantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_Enfermedad != null ? id_Enfermedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Enfermedad)) {
            return false;
        }
        Enfermedad other = (Enfermedad) object;
        if ((this.id_Enfermedad == null && other.id_Enfermedad != null)
                || (this.id_Enfermedad != null && !this.id_Enfermedad.equals(other.id_Enfermedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enfermedad [id_Enfermedad=" + id_Enfermedad + ", enfermedad=" + enfermedad;
    }

}
