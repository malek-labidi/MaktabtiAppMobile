/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author wassim
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom ; 
    private static String prenom; 
    private static String email;
    private static String mot_de_passe ;
    private static int num_telephone ;
    private static String role ;


    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("cin",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("cin",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }
    
    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }

    public static String getPrenom() {
        return pref.get("prenom",prenom);
    }

    public static void setprenom(String prenom) {
         pref.set("prenom",prenom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getMot_de_passe() {
        return pref.get("mot de passe",mot_de_passe);

    }

    public static void setMot_de_passe(String mot_de_passe) {
         pref.set("mot de passe",mot_de_passe);
    }

    public static int getNum_telephone() {
        return pref.get("num telephone",num_telephone);
    }

    public static void setNum_telephone(int num_telephone) {
         pref.set("num telephone",num_telephone);
    }

    public static String getRole() {
        return pref.get("role",role);
    }

    public static void setRole(String role) {
         pref.set("role",role);
    }


   
}
