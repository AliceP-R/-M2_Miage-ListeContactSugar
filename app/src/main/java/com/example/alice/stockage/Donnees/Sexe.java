package com.example.alice.stockage.Donnees;

/**
 * Created by Alice on 19/12/2015.
 */
public enum Sexe
{
    HOMME("Homme"),
    FEMME("Femme");

    private String type = "";
    Sexe(String s)
    {
        this.type = s;
    }

    @Override
    public String toString() {
        return type;

    }
}
