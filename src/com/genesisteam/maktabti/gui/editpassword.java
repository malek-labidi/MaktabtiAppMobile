/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;

/**
 *
 * @author wassi
 */
public class editpassword extends BaseForm{
      public editpassword(Resources res) {
        setTitle("Modifier Profil");
        setScrollableY(false);

          Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

      Command back = new Command("",backIcon) {
        @Override
        public void actionPerformed(ActionEvent evt) {
            new editprofile(res).showBack();
        }
    };
    getToolbar().addCommandToLeftBar(back);

        SessionManager u = new SessionManager();

      
        TextField tfpassword = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        
        
        
        

        Button Save = new Button("Enregistrer");

        
        Save.getAllStyles().setBgTransparency(0);
        Save.getAllStyles().setFgColor(0x00377E);
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
            Dialog.show("Success","Votre mot de passe est modifié !","OK",null);
            new Home(res).show();

        });
        
        this.addAll(tfpassword,buttonloginContainer);
 setLayout(new FlowLayout(Component.CENTER));
    }
}
