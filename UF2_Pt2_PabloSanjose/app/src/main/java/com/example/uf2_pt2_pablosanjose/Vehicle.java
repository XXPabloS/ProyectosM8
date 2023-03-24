package com.example.uf2_pt2_pablosanjose;

public class Vehicle {
    private String nom;
    private String cognoms;
    private int telefon;
    private String marca;
    private String model;
    private String matricula;

    public Vehicle() {
    }

    public Vehicle(String nom, String cognoms, int telefon, String marca, String model, String matricula) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.telefon = telefon;
        this.marca = marca;
        this.model = model;
        this.matricula = matricula;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}

