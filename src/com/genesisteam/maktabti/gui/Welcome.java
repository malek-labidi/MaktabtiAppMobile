/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;


/**
 *
 * @author admin
 */
public class Welcome extends Form{
    
     public Welcome() throws IOException {
         setUIID("WELCOMEPAGE");
        setBgImage(EncodedImage.create("/home-img.png"));
         EncodedImage enc = EncodedImage.create("/logo.png");
            ImageViewer imgv = new ImageViewer(enc);
             //imgv.getAllStyles().setMarginTop(30);
      
      

    Label welcomeLabel = new Label("Bienvenue");
    welcomeLabel.getAllStyles().setFgColor(0xFFFFFF);
    welcomeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
    welcomeLabel.getAllStyles().setMarginTop(20);
    welcomeLabel.getAllStyles().setAlignment(Component.CENTER);
    Container container = new Container(new BorderLayout());
//container.add(BorderLayout.NORTH, welcomeLabel);
    //add(BorderLayout.NORTH, welcomeLabel);

    Label descriptionLabel = new Label("lire sans limite");
    descriptionLabel.getAllStyles().setFgColor(0xFFFFFF);
    descriptionLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
    descriptionLabel.getAllStyles().setAlignment(Component.CENTER);
        //Container container = new Container(new BorderLayout());
//container.add(BorderLayout.NORTH, descriptionLabel);

    Button loginButton = new Button("Se connecter");
    Button signupButton = new Button("S'inscrire");

    // Add login and signup buttons to a flow layout container
    Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
    buttonsContainer.getAllStyles().setPaddingTop(20);
    buttonsContainer.add(loginButton);
    buttonsContainer.add(signupButton);
         // container.add(BorderLayout.SOUTH, buttonsContainer);
         this.addAll(imgv,welcomeLabel,descriptionLabel,buttonsContainer);
     }
    
}
