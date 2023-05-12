/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.competition;

import com.genesisteam.maktabti.gui.competition.GetCompetitions;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.QuestionService;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author admin
 */
public class CompetitionDetails extends BaseForm {
    private Resources theme ; 
    QuestionService qs = QuestionService.getInstance();
     Calendar now = Calendar.getInstance();
  
        public CompetitionDetails(Competition competition,Resources res) {
        setTitle(competition.getNom());
        
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
            
            try {
               image = URLImage.createToStorage(
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
        


Label recompenseLabel = new Label("Recompense :"+competition.getRecompense());
recompenseLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(recompenseLabel);

Label livreLabel = new Label("Livre :"+competition.getIdLivre());
livreLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(livreLabel);

Label lienLabel = new Label("Lien :"+competition.getLienCompetition());
lienLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(lienLabel);

Label debutLabel = new Label("Date Début :"+competition.getDateDebut().toString());
debutLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(debutLabel);

Label finLabel = new Label("Date Fin :"+competition.getDateFin().toString());
finLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(finLabel);

         Button participateButton = new Button("Participer");
            participateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<Question> questions = qs.fetchQuestions(competition.getIdCompetition());
                    new getQuestions(questions,res).show();
                }
            });
            Calendar calendar = Calendar.getInstance();
            Calendar debut = Calendar.getInstance();
            debut.setTime(competition.getDateDebut());
            Calendar fin = Calendar.getInstance();
            fin.setTime(competition.getDateFin());
            if (debut.after(now) || fin.before(now)) {
                // competition is closed
                Label closedLabel = new Label("Compétition fermée");
                closedLabel.getAllStyles().setFgColor(0xFF0000);
                 content.add(closedLabel);

            } else {
                 content.add(participateButton);


            }
           
        // add content to form
        this.add(content);
        
      

  
    }
        
    
}

