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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
    Container imgContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    imgContainer.add(imgv);
////////////////////////////////

        TextField username = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        
        Button signIn = new Button("Se connecter");
        Button signUp = new Button("S'inscrire");

        signIn.getAllStyles().setBgTransparency(0);
        signIn.getAllStyles().setFgColor(000000);
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
       
        imgContainer.getAllStyles().setMarginTop(500);
        imgContainer.getAllStyles().setAlignment(CENTER);
        


        signIn.addActionListener(e -> 
        {
             UtilisateurService.getInstance().signin(username, password, null);
             
             new Home(res).show();

        });

                this.addAll(imgContainer,username, password, buttonloginContainer,buttonsContainer);

         setLayout(new FlowLayout(Component.CENTER));
        
        
        

        
    }
    
}
