/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;

/**
 *
 * @author wassi
 */
public class editpassword extends BaseForm{
      public editpassword(Resources res) {
        setTitle("Modifier Profile");
        setScrollableY(true);

        setUIID("Update Profile Page");
        try {
            setBgImage(EncodedImage.create("/home-img.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        SessionManager u = new SessionManager();

      
        TextField tfpassword = new TextField("", "Password 1", 20, TextField.PASSWORD);
        
        
        
        

        Button Save = new Button("Save");

        
        Save.getAllStyles().setBgTransparency(0);
        Save.getAllStyles().setFgColor(0xFFFFFF);
        Border roundedBorder = Border.createRoundBorder(100, 100);
        Save.getAllStyles().setBorder(roundedBorder);
        Save.getAllStyles().setPaddingRight(20);
        Save.getAllStyles().setPaddingLeft(20);
        Container buttonloginContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        buttonloginContainer.add(Save);
        tfpassword.getAllStyles().setAlignment(Component.CENTER);

        //Rounded Border
        Border roundedBorder1 = Border.createRoundBorder(100, 100);
        tfpassword.getAllStyles().setBorder(roundedBorder);
        Border roundedBorder2 = Border.createRoundBorder(100, 100);

        Save.addActionListener((e) -> {
            UtilisateurService.getInstance().updatepassword(SessionManager.getId(),tfpassword);
            Dialog.show("Success","account is saved","OK",null);
            new Home(res).show();

        });
        
        this.addAll(tfpassword,buttonloginContainer);

    }
}
