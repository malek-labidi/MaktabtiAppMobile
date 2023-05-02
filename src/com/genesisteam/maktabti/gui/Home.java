/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import java.io.IOException;


/**
 *
 * @author wassi
 */
public class Home extends Form {

    Form current;
    private Resources theme;
    private EncodedImage enc;

    public Home() throws IOException {

        setTitle("Maktabti App");
        setScrollableY(true);
        setUIID("HOMEPAGE");
        setBgImage(EncodedImage.create("/home-img.png"));
         getTitleComponent().getStyle().setFgColor(ColorUtil.BLUE); 
    getToolbar().getStyle().setBgColor(ColorUtil.BLUE); 
    


        getToolbar().addCommandToSideMenu("Acceuil", null, e -> {
            this.show();
        });
        getToolbar().addCommandToSideMenu("Liste des Livres", null, e -> {
            //new GetOffres().show();
        });
        getToolbar().addCommandToSideMenu("Liste des Compétitions", null, e -> {
            new ShowCompetition().show();
        });
        getToolbar().addCommandToSideMenu("Liste des Evenements", null, e -> {
            new ShowEvenement().show();
        });
        getToolbar().addCommandToSideMenu("Liste des offres", null, e -> {
            //new ProduitsListe().show();
        });
        getToolbar().addCommandToSideMenu("Réclamations", null, e -> {
            //new ProduitAjout().show();
        });
        getToolbar().addCommandToSideMenu("Ajouter Offre", null, e -> {
            //new AddOffres().show();
        });
        getToolbar().addCommandToSideMenu("Login", null, e -> {
              new Login().show();
        });
        getToolbar().addCommandToSideMenu("Register", null, e -> {
              new Register().show();
        });
        getToolbar().addCommandToSideMenu("Profile", null, e -> {
            //new ProfileForm().show();

        });
        getToolbar().addCommandToSideMenu("Logout", null, e -> {
            //new SignInForm().show();
            //SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
            //System.out.println(SessionManager.getEmail());  
            try {
                new Welcome().show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });

        enc = EncodedImage.create("/maktabti.png");

        //Image img=URLImage.createToStorage(enc,"http://localhost/img/shared.png","http://localhost/img/shared.png");
        ImageViewer imgv = new ImageViewer(enc);
        add(imgv);
    }
}
