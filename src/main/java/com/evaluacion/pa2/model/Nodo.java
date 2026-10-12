package com.evaluacion.pa2.model;

/**
 * Nodo de la Lista Enlazada Simple.
 * Representa una solicitud de atención en la secuencia:
 * - dato: SolicitudAtencion (codigo, solicitante, estado)
 * - siguiente: enlace al siguiente nodo
 */
public class Nodo {

    private SolicitudAtencion dato;
    private Nodo siguiente;

    public Nodo(SolicitudAtencion dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Nodo(int codigo, String solicitante, String estado) {
        this.dato = new SolicitudAtencion(codigo, solicitante, estado);
        this.siguiente = null;
    }

    public Nodo(SolicitudAtencion dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public SolicitudAtencion getDato() {
        return dato;
    }

    public void setDato(SolicitudAtencion dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    // Métodos delegados para acceso directo
    public int getCodigo() {
        return dato.getCodigo();
    }

    public String getSolicitante() {
        return dato.getSolicitante();
    }

    public String getEstado() {
        return dato.getEstado();
    }

    @Override
    public String toString() {
        return dato != null ? dato.toString() : "[null]";
    }
}
