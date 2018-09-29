package com.example.a20151inf0182.organizeme.Entidades;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 20151inf0182 on 27/09/2018.
 */

public class Tarefas implements Serializable{
    private String aFazer;
    private String nomeTarefa;
    private String materia;
    private String tempoEntrega;
    private String tempoPrevisto;
    private ArrayList<String[]> integrantes;
    private String proprietario;
    private String subTarefaProp;
    private String status;



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

    public ArrayList<String[]> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String[]> integrantes) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tarefas{" +
                "aFazer='" + aFazer + '\'' +
                ", nomeTarefa='" + nomeTarefa + '\'' +
                ", materia='" + materia + '\'' +
                ", tempoEntrega='" + tempoEntrega + '\'' +
                ", tempoPrevisto='" + tempoPrevisto + '\'' +
                ", integrantes=" + integrantes +
                ", proprietario='" + proprietario + '\'' +
                ", subTarefaProp='" + subTarefaProp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
