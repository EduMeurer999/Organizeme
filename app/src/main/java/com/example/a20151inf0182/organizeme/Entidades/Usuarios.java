package com.example.a20151inf0182.organizeme.Entidades;


import java.io.Serializable;

/**
 * Created by 20151inf0115 on 04/09/2018.
 */

public class Usuarios implements Serializable{

    private String id;
    private String email;
    private String senha;
    private String nome;
    private String curso;
    private String dataNascimento;
    private String serie;
    private String livreInicio;
    private String livreFim;
    private Tarefas tarefas;



    public Usuarios(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Tarefas getTarefas() {
        return tarefas;
    }

    public void setTarefas(Tarefas tarefas) {
        this.tarefas = tarefas;
    }

    public String getLivreInicio() {
        return livreInicio;
    }

    public void setLivreInicio(String livreInicio) {
        this.livreInicio = livreInicio;
    }

    public String getLivreFim() {
        return livreFim;
    }

    public void setLivreFim(String livreFim) {
        this.livreFim = livreFim;
    }
}
