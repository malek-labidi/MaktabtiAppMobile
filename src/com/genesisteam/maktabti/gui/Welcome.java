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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class Welcome extends BaseForm {

    public Welcome(Resources res) {
        // set the UIID and background image of the form
        setScrollableY(false);
        setUIID("WELCOMEPAGE");
        try {
            setBgImage(EncodedImage.create("/home-img.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // create the image viewer and add it to a container
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
    
    // Add the container to the form
    //addComponent(BorderLayout.CENTER, imgContainer);

        // create the welcome label and add it to a container
        Label welcomeLabel = new Label("Bienvenue Chez Maktabti");
        welcomeLabel.getAllStyles().setFgColor(0xFFFFFF);
        welcomeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Container welcomeContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        welcomeContainer.add(welcomeLabel);

        // create the description label and add it to a container
        Label descriptionLabel = new Label("Obtenez votre livre préféré");
        descriptionLabel.getAllStyles().setFgColor(0xFFFFFF);
        descriptionLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);

        // create the login and signup buttons and add them to a container
        Button loginButton = new Button("Se connecter");
         loginButton.addActionListener(e -> {
    new Login(res).show();
});
        loginButton.getAllStyles().setBgTransparency(0);
        loginButton.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        loginButton.getAllStyles().setBorder(roundedBorder);
        loginButton.getAllStyles().setPaddingRight(20);
        loginButton.getAllStyles().setPaddingLeft(20);
        Container buttonloginContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonloginContainer.add(loginButton);

        Button signupButton = new Button("S'inscrire");
        signupButton.addActionListener(e -> {
    new Register(res).show();
});
        signupButton.getAllStyles().setBgColor(0x00377E);
        signupButton.getAllStyles().setFgColor(0x00377E);
        signupButton.getAllStyles().setBorder(roundedBorder);
        signupButton.getAllStyles().setPaddingRight(20);
        signupButton.getAllStyles().setPaddingLeft(20);
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonsContainer.add(signupButton);
       

        // set the styles and alignment of the containers
        imgContainer.getAllStyles().setMarginTop(500);
        imgContainer.getAllStyles().setAlignment(Component.CENTER);
        welcomeContainer.getAllStyles().setMarginTop(20);
        welcomeContainer.getAllStyles().setAlignment(Component.CENTER);
        descriptionContainer.getAllStyles().setAlignment(Component.CENTER);
        buttonsContainer.getAllStyles().setPaddingTop(200);
        buttonsContainer.getAllStyles().setAlignment(Component.CENTER);
        buttonloginContainer.getAllStyles().setPaddingTop(50);
         buttonloginContainer.getAllStyles().setPaddingBottom(20);
        buttonloginContainer.getAllStyles().setAlignment(Component.CENTER);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // add the containers to the form
        add(imgContainer);
        add(welcomeContainer);
        add(descriptionContainer);
        add(buttonsContainer);
        add(buttonloginContainer);

        // center the content on the form
        setLayout(new FlowLayout(Component.CENTER));

    }

}
