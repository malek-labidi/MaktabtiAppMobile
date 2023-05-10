/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Competition {
    private int idCompetition;
    private String nom , lienCompetition,listePaticipants,idLivre,image,recompense;
    private Date dateDebut,dateFin;

    public Competition() {
    }

    public Competition(int idCompetition, String nom, String lienCompetition, String listePaticipants, String idLivre, String image, String recompense, Date dateDebut, Date dateFin) {
        this.idCompetition = idCompetition;
        this.nom = nom;
        this.lienCompetition = lienCompetition;
        this.listePaticipants = listePaticipants;
        this.idLivre = idLivre;
        this.image = image;
        this.recompense = recompense;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Competition(String nom, String lienCompetition, String listePaticipants, String idLivre, String image, String recompense, Date dateDebut, Date dateFin) {
        this.nom = nom;
        this.lienCompetition = lienCompetition;
        this.listePaticipants = listePaticipants;
        this.idLivre = idLivre;
        this.image = image;
        this.recompense = recompense;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLienCompetition() {
        return lienCompetition;
    }

    public void setLienCompetition(String lienCompetition) {
        this.lienCompetition = lienCompetition;
    }

    public String getListePaticipants() {
        return listePaticipants;
    }

    public void setListePaticipants(String listePaticipants) {
        this.listePaticipants = listePaticipants;
    }

    public String getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(String idLivre) {
        this.idLivre = idLivre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRecompense() {
        return recompense;
    }

    public void setRecompense(String recompense) {
        this.recompense = recompense;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Competition{" + "idCompetition=" + idCompetition + ", nom=" + nom + ", lienCompetition=" + lienCompetition + ", listePaticipants=" + listePaticipants + ", idLivre=" + idLivre + ", image=" + image + ", recompense=" + recompense + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
    
}
