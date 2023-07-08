package com.daemontech.sgct_mobile.modelos;

import java.time.LocalDate;

public class Ternera {

    private Long id_ternera;
    private Integer caravana_ternera;
    private Integer id_padr_tern;
    private Integer id_madr_tern;
    private LocalDate fec_alt_tern;
    private LocalDate fec_baj_tern;
    private LocalDate fec_nac_tern;
    private Double peso_act;
    private Double peso_nac;
    private String raza_ternera;

    private String motivo_baja_ternera;
    private String tipo_parto_ternera;
    private boolean eliminado;

    public Ternera(Long id_ternera, Integer caravana_ternera, Integer id_padr_tern, Integer id_madr_tern, LocalDate fec_alt_tern, LocalDate fec_baj_tern, LocalDate fec_nac_tern, Double peso_act, Double peso_nac, String raza_ternera, String tipo_parto_ternera, boolean eliminado) {
        this.id_ternera = id_ternera;
        this.caravana_ternera = caravana_ternera;
        this.id_padr_tern = id_padr_tern;
        this.id_madr_tern = id_madr_tern;
        this.fec_alt_tern = fec_alt_tern;
        this.fec_baj_tern = fec_baj_tern;
        this.fec_nac_tern = fec_nac_tern;
        this.peso_act = peso_act;
        this.peso_nac = peso_nac;
        this.raza_ternera = raza_ternera;
        this.tipo_parto_ternera = tipo_parto_ternera;
        this.eliminado = eliminado;
    }

    public Ternera(Long id_ternera, Integer caravana_ternera, LocalDate fec_nac_tern, LocalDate fec_alt_tern,Double peso_nac, Double peso_act, String raza_ternera, String tipo_parto_ternera) {
        this.id_ternera = id_ternera;
        this.caravana_ternera = caravana_ternera;
        this.fec_alt_tern = fec_alt_tern;
        this.fec_nac_tern = fec_nac_tern;
        this.peso_nac = peso_nac;
        this.peso_act = peso_act;
        this.raza_ternera = raza_ternera;
        this.tipo_parto_ternera = tipo_parto_ternera;
    }

    public Ternera() {
        super();
    }

    public Ternera(int caravana_ternera, int id_padr_tern, int id_madr_tern, String fec_alt_tern, String fec_nac_tern, Double peso_nac, Double peso_act) {
    }

    public Ternera(int caravana_ternera, int id_padr_tern, int id_madr_tern, String fec_alt_tern, String fec_nac_tern, Double peso_nac, Double peso_act, String fec_nac_tern1, String fec_nac_tern2) {
    }

    public Ternera(long id_ternera, int caravana_ternera, int id_padr_tern, int id_madr_tern, String fec_alt_tern, String fec_nac_tern, Double peso_nac, Double peso_act, String tipo_parto_ternera, String motivo_baja_ternera) {
    }

    public Ternera(Long id_ternera, Integer caravana_ternera, Integer id_padr_tern, Integer id_madr_tern, LocalDate fec_alt_tern, LocalDate fec_baj_tern, LocalDate fec_nac_tern, Double peso_act, Double peso_nac, String raza_ternera, String motivo_baja_ternera, String tipo_parto_ternera, boolean eliminado) {
        this.id_ternera = id_ternera;
        this.caravana_ternera = caravana_ternera;
        this.id_padr_tern = id_padr_tern;
        this.id_madr_tern = id_madr_tern;
        this.fec_alt_tern = fec_alt_tern;
        this.fec_baj_tern = fec_baj_tern;
        this.fec_nac_tern = fec_nac_tern;
        this.peso_act = peso_act;
        this.peso_nac = peso_nac;
        this.raza_ternera = raza_ternera;
        this.motivo_baja_ternera = motivo_baja_ternera;
        this.tipo_parto_ternera = tipo_parto_ternera;
        this.eliminado = eliminado;
    }

    public Long getId_ternera() {
        return id_ternera;
    }

    public void setId_ternera(Long id_ternera) {
        this.id_ternera = id_ternera;
    }

    public Integer getCaravana_ternera() {
        return caravana_ternera;
    }

    public void setCaravana_ternera(Integer caravana_ternera) {
        this.caravana_ternera = caravana_ternera;
    }

    public Integer getId_padr_tern() {
        return id_padr_tern;
    }

    public void setId_padr_tern(Integer id_padr_tern) {
        this.id_padr_tern = id_padr_tern;
    }

    public Integer getId_madr_tern() {
        return id_madr_tern;
    }

    public void setId_madr_tern(Integer id_madr_tern) {
        this.id_madr_tern = id_madr_tern;
    }

    public LocalDate getFec_alt_tern() {
        return fec_alt_tern;
    }

    public void setFec_alt_tern(LocalDate fec_alt_tern) {
        this.fec_alt_tern = fec_alt_tern;
    }

    public LocalDate getFec_baj_tern() {
        return fec_baj_tern;
    }

    public void setFec_baj_tern(LocalDate fec_baj_tern) {
        this.fec_baj_tern = fec_baj_tern;
    }

    public LocalDate getFec_nac_tern() {
        return fec_nac_tern;
    }

    public void setFec_nac_tern(LocalDate fec_nac_tern) {
        this.fec_nac_tern = fec_nac_tern;
    }

    public Double getPeso_act() {
        return peso_act;
    }

    public void setPeso_act(Double peso_act) {
        this.peso_act = peso_act;
    }

    public Double getPeso_nac() {
        return peso_nac;
    }

    public void setPeso_nac(Double peso_nac) {
        this.peso_nac = peso_nac;
    }

    public String getRaza_ternera() {
        return raza_ternera;
    }

    public void setRaza_ternera(String raza_ternera) {
        this.raza_ternera = raza_ternera;
    }

    public String getMotivo_baja_ternera() {
        return motivo_baja_ternera;
    }

    public void setMotivo_baja_ternera(String motivo_baja_ternera) {
        this.motivo_baja_ternera = motivo_baja_ternera;
    }

    public String getTipo_parto_ternera() {
        return tipo_parto_ternera;
    }

    public void setTipo_parto_ternera(String tipo_parto_ternera) {
        this.tipo_parto_ternera = tipo_parto_ternera;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}


