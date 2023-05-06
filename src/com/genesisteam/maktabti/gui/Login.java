/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;

/**
 *
 * @author wassim
 */
public class Login extends Form{
  public Login() {
        

        setTitle("S'authentifier");
        setScrollableY(true);
      


        TextField username = new TextField("", "email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("S'authentifier");
        Button signUp = new Button("Vous n'avez pas de compte ? Créer un compte");

        

        
        signUp.addActionListener(e -> new Register().show());
        
        
        
        signIn.addActionListener(e -> 
        {
             UtilisateurService.getInstance().signin(username, password, null);

        });

                this.addAll(username, password, signIn,signUp);

        
        
        
        

        
    }
    
}
