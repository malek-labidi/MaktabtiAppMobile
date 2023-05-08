/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.entities;

/**
 *
 * @author Gaaloul
 */
public class Offre {
     private int id_offre;
    private int id_livre;
    private String pourcentage_solde;
    private float prix_solde;
    public Offre(){
        
    }

    public Offre(int id_livre, String pourcentage_solde, float prix_soldé) {
        this.id_livre = id_livre;
        this.pourcentage_solde = pourcentage_solde;
        this.prix_solde = prix_solde;
    }

    public Offre(int id_offre, int id_livre, String pourcentage_solde, float prix_soldé) {
        this.id_offre = id_offre;
        this.id_livre = id_livre;
        this.pourcentage_solde = pourcentage_solde;
        this.prix_solde = prix_solde;
    }

    public int getId_offre() {
        return id_offre;
    }

    public int getId_livre() {
        return id_livre;
    }

    public String getPourcentage_solde() {
        return pourcentage_solde;
    }

    public float getPrix_soldé() {
        return prix_solde;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public void setPourcentage_solde(String pourcentage_solde) {
        this.pourcentage_solde = pourcentage_solde;
    }

    public void setPrix_soldé(float prix_soldé) {
        this.prix_solde = prix_solde;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", id_livre=" + id_livre + ", pourcentage_solde=" + pourcentage_solde + ", prix_sold\u00e9=" + prix_solde + '}';
    }
}