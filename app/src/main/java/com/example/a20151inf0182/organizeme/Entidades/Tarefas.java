package com.example.a20151inf0182.organizeme.Entidades;

/**
 * Created by 20151inf0182 on 27/09/2018.
 */

public class Tarefas {
    private String aFazer;
    private String materia;
    private String tempoEntrega;
    private String tempoPrevisto;
    private Usuarios[] integrantes;
    private String proprietario;

    public String getaFazer() {
        return aFazer;
    }

    public void setaFazer(String aFazer) {
        this.aFazer = aFazer;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(String tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    public String getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(String tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public Usuarios[] getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(Usuarios[] integrantes) {
        this.integrantes = integrantes;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }
}
