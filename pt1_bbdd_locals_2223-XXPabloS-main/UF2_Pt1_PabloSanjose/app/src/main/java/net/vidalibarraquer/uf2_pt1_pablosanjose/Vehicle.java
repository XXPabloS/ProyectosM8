package net.vidalibarraquer.uf2_pt1_pablosanjose;

public class Vehicle {
    private String nom;
    private String cognom;
    private String telefon;
    private String marca;
    private String model;
    private String matricula;

    public Vehicle() {
    }

    public Vehicle( String nom, String cognom, String telefon, String marca, String model, String matricula) {
        this.nom = nom;
        this.cognom = cognom;
        this.telefon = telefon;
        this.marca = marca;
        this.model = model;
        this.matricula = matricula;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public String getMatricula() {
        return matricula;
    }
}

