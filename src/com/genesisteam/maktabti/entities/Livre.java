/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Saleh
 */
public class Livre {
    private int idLivre;
    private String idAuteur;
    private String idCategorie;
    private String titre;
    private Date datePub;
    private String langue;
    private int isbn;
    private int nbPages;
    private String resume;
    private float prix;
    private InputStream image;
  
    public Livre (){

}  
  public Livre(String id_auteur, String id_categorie, String titre, Date date_pub, String langue, int isbn, int nb_pages, String resume, float prix, InputStream image) {
        this.idAuteur = id_auteur;
        this.idCategorie = id_categorie;
        this.titre = titre;
        this.datePub = date_pub;
        this.langue = langue;
        this.isbn = isbn;
        this.nbPages = nb_pages;
        this.resume = resume;
        this.prix = prix;
        this.image=image;
    }   
  public Livre(int id_livre, String id_auteur, String id_categorie, String titre, Date date_pub, String langue, int isbn, int nb_pages, String resume, float prix,InputStream image) {
        this.idLivre = id_livre;
        this.idAuteur = id_auteur;
        this.idCategorie = id_categorie;
        this.titre = titre;
        this.datePub = date_pub;
        this.langue = langue;
        this.isbn = isbn;
        this.nbPages = nb_pages;
        this.resume = resume;
        this.prix = prix;
        this.image=image;
    }
      public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(String idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
    

    @Override
    public String toString() {
        return "Livre{" + "id_auteur=" + idAuteur + ", id_categorie=" + idCategorie + ", titre=" + titre + ", date_pub=" + datePub + ", langue=" + langue + ", isbn=" + isbn + ", nb_pages=" + nbPages + ", resume=" + resume + ", prix=" + prix + '}';
    }
    
}
