package com.example.a20151inf0182.organizeme.Entidades;

import java.util.ArrayList;

/**
 * Created by 20151inf0182 on 27/09/2018.
 */

public class Tarefas {
    private String aFazer;
    private String nomeTarefa;
    private String materia;
    private String tempoEntrega;
    private String tempoPrevisto;
    private ArrayList<ArrayList<String>> integrantes;
    private String proprietario;
    private String subTarefaProp;



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

    public ArrayList<ArrayList<String>> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<ArrayList<String>> integrantes) {
        this.integrantes = integrantes;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getSubTarefaProp() {
        return subTarefaProp;
    }

    public void setSubTarefaProp(String subTarefaProp) {
        this.subTarefaProp = subTarefaProp;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
