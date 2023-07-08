package com.daemontech.sgct_mobile.modelos;

import com.daemontech.sgct_mobile.modelos.entityKey.Enfermedad_R_Ternera_R_Variante_PK;

import java.time.LocalDate;

public class EnfTerVar {

    private int caravana_ternera;
    private String enfermedad;
    private String variante;
    private String severidad;
    private String fechaInicio;
    private String fechaFin;
    private String motivoFin;

    public EnfTerVar(int caravana_ternera, String enfermedad, String variante, String severidad, String fechaInicio, String fechaFin, String motivoFin) {
        this.caravana_ternera = caravana_ternera;
        this.enfermedad = enfermedad;
        this.variante = variante;
        this.severidad = severidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivoFin = motivoFin;
    }

    public int getCaravana_ternera() {
        return caravana_ternera;
    }

    public void setCaravana_ternera(int caravana_ternera) {
        this.caravana_ternera = caravana_ternera;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getVariante() {
        return variante;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMotivoFin() {
        return motivoFin;
    }

    public void setMotivoFin(String motivoFin) {
        this.motivoFin = motivoFin;
    }
}
