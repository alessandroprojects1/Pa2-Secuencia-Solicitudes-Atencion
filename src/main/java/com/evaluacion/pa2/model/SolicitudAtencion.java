package com.evaluacion.pa2.model;

/**
 * Modelo de datos para la Solicitud de Atención acordado como base común del equipo:
 * - codigo: entero, único (identificador del caso)
 * - solicitante: texto
 * - estado: Pendiente / En atención / Resuelto
 */
public class SolicitudAtencion {

    private int codigo;
    private String solicitante;
    private String estado;

    public SolicitudAtencion(int codigo, String solicitante, String estado) {
        this.codigo = codigo;
        this.solicitante = solicitante;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("[%d | %s | %s]", codigo, solicitante, estado);
    }

    public String toFilaTabla() {
        return String.format("| %-8d | %-20s | %-16s |", codigo, solicitante, estado);
    }
}
