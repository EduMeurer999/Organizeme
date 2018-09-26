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
    private int idade;
    private int serie;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }




}
