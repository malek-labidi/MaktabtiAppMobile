/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;
import com.genesisteam.maktabti.entities.Utilisateur;

/**
 *
 * @author wassim
 */
public class Register extends BaseForm {
    
    public Register() {
        setTitle("S'enregistrer");
        setScrollableY(true);
        getToolbar().addCommandToRightBar("Retour", null, ev->{
            try {
                new Home().show();
            } catch (IOException ex) {
                System.out.println("");
            }
        });

      
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField numtelephone = new TextField("", "Numéro de téléphone", 20, TextField.ANY);
        TextField role = new TextField("", "Role", 20, TextField.ANY);
        
        
        
        
        
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        numtelephone.setSingleLineTextArea(false);
        role.setSingleLineTextArea(false);
        Button next = new Button("Register");
        Button signIn = new Button("Vous avez déja un compte ? \n S'authentifier");
        
        signIn.addActionListener(e -> new Login().show());
        

        next.addActionListener((e) -> {
            UtilisateurService.getInstance().signup(nom,prenom,email,password, numtelephone,role,null);
            Dialog.show("Success","account is saved","OK",null);
            new Login().show();
        });
        
        this.addAll(nom, prenom, email,password,numtelephone,role,next,signIn);

    }
}
