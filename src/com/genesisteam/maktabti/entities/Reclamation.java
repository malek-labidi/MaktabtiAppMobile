/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

/**
 *
 * @author Ilef
 */
public class Reclamation {
    private int idReclamation;
    private String message;
    private String feedback;
    private Utilisateur user;
    private byte[] image;

    public Reclamation() {
    }

    
    public Reclamation(String message, String feedback, Utilisateur user, byte[] image) {
        this.message = message;
        this.feedback = feedback;
        this.user = user;
        this.image = image;
    }

    
    
    public Reclamation(int idReclamation, String message, String feedback, Utilisateur user, byte[] image) {
        this.idReclamation = idReclamation;
        this.message = message;
        this.feedback = feedback;
        this.user = user;
        this.image = image;
    }

    public Reclamation(int idReclamation, String message, String feedback) {
    }

    public Reclamation(String message, String feedback) {
        this.message = message;
        this.feedback = feedback;
    }

    
    
    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    
    public int getIdReclamation() {
        return idReclamation;
    }

    public String getMessage() {
        return message;
    }

    public String getFeedback() {
        return feedback;
    }

    public Utilisateur getUser() {
        return user;
    }

    public byte[] getImage() {
        return image;
    }
    
    
    
}
