/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

/**
 *
 * @author SADOK
 */
public class Commentaire {
    private int idCommentaire,idClient,idEvenement;
    private String commentaire,nomClient;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, int idClient, int idEvenement, String commentaire) {
        this.idCommentaire = idCommentaire;
        this.idClient = idClient;
        this.idEvenement = idEvenement;
        this.commentaire = commentaire;
    }

    public Commentaire(int idEvenement, String commentaire) {
        this.idEvenement = idEvenement;
        this.commentaire = commentaire;
    }
    

    public Commentaire(int idClient, int idEvenement, String commentaire) {
        this.idClient = idClient;
        this.idEvenement = idEvenement;
        this.commentaire = commentaire;
    }

    public Commentaire(int idCommentaire, int idClient, int idEvenement, String commentaire, String nomClient) {
        this.idCommentaire = idCommentaire;
        this.idClient = idClient;
        this.idEvenement = idEvenement;
        this.commentaire = commentaire;
        this.nomClient = nomClient;
    }
    
    

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", idClient=" + idClient + ", idEvenement=" + idEvenement + ", commentaire=" + commentaire + ", nomClient=" + nomClient + '}';
    }

    
    
}
