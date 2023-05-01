/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

//import java.sql.Time;
import java.util.Date;

/**
 *
 * @author SADOK
 */
public class Evenement {
    private Double idEvenement;
    private Double nbTicket;
    private String nom,lieu,description,image;
    private Date date;
    private String heure;

    public Evenement() {
    }

    public Evenement(Double idEvenement, Double nbTicket, String nom, String lieu, String description, String image, Date date, String heure) {
        this.idEvenement = idEvenement;
        this.nbTicket = nbTicket;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.date = date;
        this.heure = heure;
    }

    public Evenement(Double nbTicket, String nom, String lieu, String description, String image, Date date, String heure) {
        this.nbTicket = nbTicket;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.image = image;
        this.date = date;
        this.heure = heure;
    }

   

    public Double getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Double idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Double getNbTicket() {
        return nbTicket;
    }

    public void setNbTicket(Double nbTicket) {
        this.nbTicket = nbTicket;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", nbTicket=" + nbTicket + ", nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", image=" + image + ", date=" + date + ", heure=" + heure + '}';
    }

   
    
    
    
    
}
