package com.project.hackathon.camara.app.model;

import retrofit2.http.Path;

/**
 * Created by matheuscatossi on 6/4/17.
 */

public class RequisitionVoted {

    String nome;
    String email;
    String cep;
    String country;

    public RequisitionVoted(){

    }

    public RequisitionVoted(String nome, String email, String cep, String country){
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.country = country;
    }
}
