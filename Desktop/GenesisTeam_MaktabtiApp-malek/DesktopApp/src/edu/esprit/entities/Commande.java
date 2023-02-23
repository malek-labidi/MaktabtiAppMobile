/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author abdelazizlahmar
 */
public class Commande {
    
  private int id_livre;
  private int id_client ;
  private int id_commande;
  
  //attributs de type énumérés 
  private Status status;
  private Etat etat;

    public Commande() {
    }

    public Commande(int id_livre, int id_client, int id_commande, Status status, Etat etat) {
        this.id_livre = id_livre;
        this.id_client = id_client;
        this.id_commande = id_commande;
        this.status = status;
        
        this.etat = etat;
    }

    public Commande(int id_livre, int id_client, Status status, Etat etat) {
        this.id_livre = id_livre;
        this.id_client = id_client;
        this.status = status;
        
        this.etat = etat;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_commande() {
        return id_commande;
    }

    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "status=" + status + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_commande;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commande other = (Commande) obj;
        if (this.id_commande != other.id_commande) {
            return false;
        }
        return true;
    }
  
    
  
}
