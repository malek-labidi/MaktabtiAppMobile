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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.genesisteam.maktabti.services.UtilisateurService;
import java.io.IOException;
import com.genesisteam.maktabti.entities.Utilisateur;
/**
 *
 * @author wassi
 */
public class editprofile extends BaseForm{
      public editprofile() {
        setTitle("Modifier Profile");
        setScrollableY(true);
        getToolbar().addCommandToRightBar("Retour", null, ev->{
            try {
                new Home().show();
            } catch (IOException ex) {
                System.out.println("");
            }
        });

        SessionManager u = new SessionManager();

      
        TextField tfnom = new TextField(u.getNom(), "nom", 20, TextField.ANY);
        TextField tfEmail = new TextField(u.getEmail(), "E-Mail", 20, TextField.ANY);
        TextField tfprenom = new TextField(u.getPrenom(), "prenom", 20, TextField.EMAILADDR);
        TextField tfPhone = new TextField(Integer.parseInt((u.getNum_telephone())), "Numéro de téléphone", 8, TextField.ANY);
        TextField tfrole = new TextField(u.getRole(), "Role", 20, TextField.ANY);
        
        
        
        

        Button Save = new Button("Save");

        

        Save.addActionListener((e) -> {
            UtilisateurService.getInstance().update(SessionManager.getId(),tfnom,tfEmail,tfprenom,tfPhone, tfrole);
            Dialog.show("Success","account is saved","OK",null);
            new Login().show();
        });
        
        this.addAll(tfnom, tfEmail, tfprenom,tfPhone,tfrole,Save);

    }
}
