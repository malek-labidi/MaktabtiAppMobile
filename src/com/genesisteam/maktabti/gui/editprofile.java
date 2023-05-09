/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
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
 * @author wassi
 */
public class editprofile extends BaseForm{
      public editprofile(Resources res) {
        setTitle("Modifier Profile");
        setScrollableY(true);

        setUIID("Update Profile Page");
        try {
            setBgImage(EncodedImage.create("/home-img.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        SessionManager u = new SessionManager();

      
        TextField tfnom = new TextField(u.getNom(), "nom", 20, TextField.ANY);
        TextField tfEmail = new TextField(u.getEmail(), "E-Mail", 20, TextField.ANY);
        TextField tfprenom = new TextField(u.getPrenom(), "prenom", 20, TextField.EMAILADDR);
        TextField tfrole = new TextField(u.getRole(), "Role", 20, TextField.ANY);
        
        
        
        

        Button Save = new Button("Save");
        Button password = new Button("Change password");

        //Design boutton Update
        Save.getAllStyles().setBgTransparency(0);
        Save.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        Save.getAllStyles().setBorder(roundedBorder);
        Save.getAllStyles().setPaddingRight(20);
        Save.getAllStyles().setPaddingLeft(20);
        Container buttonloginContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonloginContainer.add(Save);
        //DesignBoutton Changer mot de passe
        password.getAllStyles().setBgTransparency(0);
        password.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder5 = Border.createRoundBorder(100, 100);
        password.getAllStyles().setBorder(roundedBorder);
        password.getAllStyles().setPaddingRight(20);
        password.getAllStyles().setPaddingLeft(20);
        Container buttonchangepasswordcontainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonchangepasswordcontainer.add(password);
        //Design il inputs
        tfnom.getAllStyles().setAlignment(Component.CENTER);
        tfEmail.getAllStyles().setAlignment(Component.CENTER);
        tfprenom.getAllStyles().setAlignment(Component.CENTER);
        tfrole.getAllStyles().setAlignment(Component.CENTER);
        //Rounded Border
        Border roundedBorder1 = Border.createRoundBorder(100, 100);
        tfnom.getAllStyles().setBorder(roundedBorder);
        Border roundedBorder2 = Border.createRoundBorder(100, 100);
        tfEmail.getAllStyles().setBorder(roundedBorder);
        Border roundedBorder3 = Border.createRoundBorder(100, 100);
        tfprenom.getAllStyles().setBorder(roundedBorder);
        Border roundedBorder4 = Border.createRoundBorder(100, 100);
        tfrole.getAllStyles().setBorder(roundedBorder);

        Save.addActionListener((e) -> {
            UtilisateurService.getInstance().update(SessionManager.getId(),tfnom,tfEmail,tfprenom, tfrole);
            Dialog.show("Success","account is saved","OK",null);
            new Home(res).show();
        });
        password.addActionListener(e -> new editpassword(res).show());

        
        this.addAll(tfnom, tfEmail, tfprenom,tfrole,buttonloginContainer,buttonchangepasswordcontainer);

    }
}
