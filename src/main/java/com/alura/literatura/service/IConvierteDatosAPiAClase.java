package com.alura.literatura.service;

public interface IConvierteDatosAPiAClase {
    //T es un placeholder para un tipo concreto (por ejemplo, Usuario, Producto, etc.) que se definirá cuando se use el método.
    <T> T obtenerDatos(String json,Class<T> clase);
}
