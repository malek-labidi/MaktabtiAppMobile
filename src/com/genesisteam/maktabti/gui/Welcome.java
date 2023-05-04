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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class Welcome extends Form {

    public Welcome() throws IOException {
        // set the UIID and background image of the form
        setUIID("WELCOMEPAGE");
        setBgImage(EncodedImage.create("/home-img.png"));

        // create the image viewer and add it to a container
        EncodedImage enc = EncodedImage.create("/logo.png");
        ImageViewer imgv = new ImageViewer(enc);
        Container imgContainer = new Container(new FlowLayout(Component.CENTER));
        imgContainer.add(imgv);

        // create the welcome label and add it to a container
        Label welcomeLabel = new Label("Bienvenue chez Maktabti");
        welcomeLabel.getAllStyles().setFgColor(0xFFFFFF);
        welcomeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Container welcomeContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        welcomeContainer.add(welcomeLabel);

        // create the description label and add it to a container
        Label descriptionLabel = new Label("obtenez votre livre préféré");
        descriptionLabel.getAllStyles().setFgColor(0xFFFFFF);
        descriptionLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        Container descriptionContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        descriptionContainer.add(descriptionLabel);

        // create the login and signup buttons and add them to a container
        Button loginButton = new Button("Se connecter");
        loginButton.getAllStyles().setBgTransparency(0);
        loginButton.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        loginButton.getAllStyles().setBorder(roundedBorder);

        Button signupButton = new Button("S'inscrire");
        signupButton.getAllStyles().setBgColor(0x00377E);
        signupButton.getAllStyles().setFgColor(0x00377E);
        signupButton.getAllStyles().setBorder(roundedBorder);
        Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonsContainer.add(signupButton);

        buttonsContainer.add(loginButton);
       

        // set the styles and alignment of the containers
        imgContainer.getAllStyles().setMarginTop(30);
        imgContainer.getAllStyles().setAlignment(Component.CENTER);
        welcomeContainer.getAllStyles().setMarginTop(20);
        welcomeContainer.getAllStyles().setAlignment(Component.CENTER);
        descriptionContainer.getAllStyles().setAlignment(Component.CENTER);
        buttonsContainer.getAllStyles().setPaddingTop(20);
        buttonsContainer.getAllStyles().setAlignment(Component.CENTER);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // add the containers to the form
        add(imgContainer);
        add(welcomeContainer);
        add(descriptionContainer);
        add(buttonsContainer);

        // center the content on the form
        setLayout(new FlowLayout(Component.CENTER));

    }

}
