/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;

/**
 *
 * @author wassim
 */
public class Login extends BaseForm{
  public Login(Resources res) {
        

        setTitle("S'authentifier");
        setScrollableY(true);
              setUIID("Login Page");
        try {
            setBgImage(EncodedImage.create("/home-img.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }



        TextField username = new TextField("", "email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("S'authentifier");
        Button signUp = new Button("Créer un compte");

        signIn.getAllStyles().setBgTransparency(0);
        signIn.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        signIn.getAllStyles().setBorder(roundedBorder);
        signIn.getAllStyles().setPaddingRight(20);
        signIn.getAllStyles().setPaddingLeft(20);
        Container buttonloginContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonloginContainer.add(signIn);

        
        //Register Page
        signUp.getAllStyles().setBgColor(0xFFFFFF);
        signUp.getAllStyles().setBorder(roundedBorder);
        signUp.getAllStyles().setPaddingRight(20);
        signUp.getAllStyles().setPaddingLeft(20);
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonsContainer.add(signUp);
        signUp.addActionListener(e -> new Register(res).show());
        
        buttonloginContainer.getAllStyles().setPaddingTop(50);
        buttonloginContainer.getAllStyles().setPaddingBottom(20);
        buttonloginContainer.getAllStyles().setAlignment(Component.CENTER);
        buttonsContainer.getAllStyles().setPaddingTop(20);
        buttonsContainer.getAllStyles().setAlignment(Component.CENTER);
        username.getAllStyles().setAlignment(Component.CENTER);
        password.getAllStyles().setAlignment(Component.CENTER);


        signIn.addActionListener(e -> 
        {
             UtilisateurService.getInstance().signin(username, password, null);
             new Home(res).show();

        });

                this.addAll(username, password, buttonloginContainer,buttonsContainer);

        
        
        
        

        
    }
    
}
