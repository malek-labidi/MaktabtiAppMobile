/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.competition;

import com.genesisteam.maktabti.gui.competition.GetCompetitions;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.gui.BaseForm;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class CompetitionDetails extends BaseForm {
    private Resources theme ; 
    
  
        public CompetitionDetails(Competition competition,Resources res) {
        setTitle("Détails de la compétition");
         // add back button to toolbar
         Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

      Command back = new Command("",backIcon) {
        @Override
        public void actionPerformed(ActionEvent evt) {
            new GetCompetitions(res).showBack();
        }
    };
    getToolbar().addCommandToLeftBar(back);

        // widgets
        Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
          
              Image image = null;
            // EncodedImage enc = 
            try {
                image =  URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                        competition.getImage(),
                        competition.getImage(),
                        URLImage.RESIZE_SCALE_TO_FILL
                );      
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            

            // create image container and add image
            Container imageContainer = new Container();
            ImageViewer imgv = new ImageViewer(image);
            imageContainer.add(imgv);
            content.add(imageContainer);
        content.add(new Label(competition.getNom()));
        content.add(new Label(competition.getRecompense()));
        content.add(new Label(competition.getIdLivre()));
        content.add(new Label(competition.getLienCompetition()));
        content.add(new Label(competition.getDateDebut().toString()));
        content.add(new Label(competition.getDateFin().toString()));

        // add content to form
        this.add(content);
        
      

  
    }
        
    
}

