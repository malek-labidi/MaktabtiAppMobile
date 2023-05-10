/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.gui.Livre.GetLivre;
import com.genesisteam.maktabti.gui.Login;
import com.genesisteam.maktabti.gui.competition.GetCompetitions;
import com.genesisteam.maktabti.gui.evenement.ShowEvenement;
import com.genesisteam.maktabti.gui.fidelite.GetFidelite;
import com.genesisteam.maktabti.gui.reclamation.AjouterReclamation;

/**
 *
 * @author admin
 */
public class BaseForm extends Form {
    
    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

       protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("load.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
              //  sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("logo.png"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_HOME,e -> new Home(res).show());
        tb.addMaterialCommandToSideMenu("Livres", FontImage.MATERIAL_BOOK,e -> new GetLivre(res).show());
        tb.addMaterialCommandToSideMenu("Competitions", FontImage.MATERIAL_WALLET_GIFTCARD,e -> new GetCompetitions(res).show());
        tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_EVENT,e -> new ShowEvenement(res).show());
        tb.addMaterialCommandToSideMenu("Offres", FontImage.MATERIAL_SAILING,e ->{ /*new Home(res).show()*/});
        tb.addMaterialCommandToSideMenu("Fidélité", FontImage.MATERIAL_SAILING,e -> new GetFidelite(res).show());
        tb.addMaterialCommandToSideMenu("Réclamations", FontImage.MATERIAL_MESSAGE,e ->{ new AjouterReclamation(TOP).show();});
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_PERSON,e ->  new editprofile(res).show());
        tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
        new Welcome(res).show();
        SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
            System.out.println(SessionManager.getEmail());            
        });
    }
}
