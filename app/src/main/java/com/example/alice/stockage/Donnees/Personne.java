package com.example.alice.stockage.Donnees;

/**
 * Created by Alice on 19/12/2015.
 */

public class Personne {

    private String nom;
    private String prenom;
    private String numero;
    private Sexe genre;

    public Personne(Sexe genre, String nom, String numero, String prenom) {
        this.genre = genre;
        this.nom = nom;
        this.numero = numero;
        this.prenom = prenom;
    }

    public Sexe getGenre() {
        return genre;
    }

    public void setGenre(Sexe genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
