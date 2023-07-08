package com.daemontech.sgct_mobile.api;

public class Api {

    private static final String ROOT_URL = "http://192.168.1.13:8080/AEWeb3/rest/";

    public static final String URL_TERNERAS = ROOT_URL + "terneras/";
    public static final String URL_LISTAR_TERNERAS = URL_TERNERAS + "listarTerneras";
    public static final String URL_ENF_PADECIDAS = URL_TERNERAS + "listarEnfPadecidas";
    public static final String URL_CREAR_TERNERA = URL_TERNERAS + "agregarTernera";
    public static final String URL_ACTUALIZAR_TERNERA = URL_TERNERAS + "actualizarTernera";
    public static final String URL_BORRAR_TERNERA = URL_TERNERAS + "borrarTernera";

}