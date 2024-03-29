/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;
import com.genesisteam.maktabti.entities.Utilisateur;

/**
 *
 * @author wassim
 */
public class Register extends BaseForm {
    
    public Register( Resources res) {
        
        setScrollableY(false);
//////////////////////////////// create the image viewer and add it to a container
    EncodedImage enc = null;
        try {
            enc = EncodedImage.create("/logo.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    Image scaledImage = enc.scaledWidth(Display.getInstance().getDisplayWidth() / 3)
                          .scaledHeight(Display.getInstance().getDisplayHeight() / 5);
    // Create the ImageViewer with the scaled image
    ImageViewer imgv = new ImageViewer(scaledImage);
    // Create the container and add the ImageViewer to it
    Container imgContainer = new Container(new FlowLayout(Component.CENTER));
    imgContainer.add(imgv);
////////////////////////////////

      
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField numtelephone = new TextField("", "Numéro de téléphone", 20, TextField.ANY);
        TextField role = new TextField("", "Role", 20, TextField.ANY);
       
        Button next = new Button("Créer un compte");
        Button signIn = new Button("Se Connecter");
        
        
        signIn.getAllStyles().setBgTransparency(0);
        signIn.getAllStyles().setFgColor(000000);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        signIn.getAllStyles().setBorder(roundedBorder);
        signIn.getAllStyles().setPaddingRight(20);
        signIn.getAllStyles().setPaddingLeft(20);
        Container buttonloginContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonloginContainer.add(signIn);

        
        //Register Page
        next.getAllStyles().setBgColor(0xFFFFFF);
        next.getAllStyles().setBorder(roundedBorder);
        next.getAllStyles().setPaddingRight(20);
        next.getAllStyles().setPaddingLeft(20);
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonsContainer.add(next);
        
        
        buttonloginContainer.getAllStyles().setPaddingTop(50);
        buttonloginContainer.getAllStyles().setPaddingBottom(20);
        buttonloginContainer.getAllStyles().setAlignment(Component.CENTER);
        buttonsContainer.getAllStyles().setPaddingTop(20);
        buttonsContainer.getAllStyles().setAlignment(Component.CENTER);
       
        imgContainer.getAllStyles().setMarginTop(300);
       
        imgContainer.getAllStyles().setAlignment(CENTER);
        
        
        signIn.addActionListener(e -> new Login(res).show());
        

        next.addActionListener((e) -> {
            UtilisateurService.getInstance().signup(nom,prenom,email,password, numtelephone,role,null);
            Dialog.show("Success","Votre compte est crée avec succés!","OK",null);
            new Login(res).show();
        });
        
        this.addAll(imgContainer,nom, prenom, email,password,numtelephone,role,buttonsContainer,buttonloginContainer);
 setLayout(new FlowLayout(Component.CENTER));
    }
}
