/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.competition;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.CompetitionService;
import com.genesisteam.maktabti.services.QuestionService;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author admin
 */
public class GetCompetitions extends BaseForm {

    CompetitionService cs = CompetitionService.getInstance();
    QuestionService qs = QuestionService.getInstance();
    private Resources theme;

//Date now = new Date();
    Calendar now = Calendar.getInstance();

    public GetCompetitions(Resources res) {

        setTitle("Liste des Compétitions");
        setScrollableY(true);
        super.addSideMenu(res);

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Competition c : cs.fetchCompetitions()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label nomLabel = new Label(c.getNom());
nomLabel.getAllStyles().setFgColor(0x00377E); 
content.add(nomLabel);
            //content.add(new Label(c.getRecompense()));
          Label debutLabel = new Label(c.getDateDebut().toString());
debutLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(debutLabel);

Label finLabel = new Label(c.getDateFin().toString());
finLabel.getAllStyles().setFgColor(0xD4A373); 
content.add(finLabel);

            // create image
            Image image = null;
            // EncodedImage enc = 
            try {
                image = URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                        c.getImage(),
                        c.getImage(),
                        URLImage.RESIZE_SCALE_TO_FILL
                );
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            // create image container and add image
            Container imageContainer = new Container();
            ImageViewer imgv = new ImageViewer(image);
            imageContainer.add(imgv);

            // create button with icon
            Button detailsButton = new Button();
            FontImage.setMaterialIcon(detailsButton, FontImage.MATERIAL_INFO);
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Competition competition = cs.getCompetition(c.getIdCompetition());
                    new CompetitionDetails(competition, res).show();
                }
            });

            Button participateButton = new Button("Participer");
            participateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  List<Question> questions = qs.fetchQuestions(c.getIdCompetition());
                new getQuestions(questions, res).show();

                   
                }
            });
            

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            card.add(BorderLayout.WEST, imageContainer);
            card.add(BorderLayout.EAST, detailsButton);
            Calendar calendar = Calendar.getInstance();
            Calendar debut = Calendar.getInstance();
            debut.setTime(c.getDateDebut());
            Calendar fin = Calendar.getInstance();
            fin.setTime(c.getDateFin());

            if (debut.after(now) || fin.before(now)) {
                // competition is closed
                Label closedLabel = new Label("Compétition fermée");
                closedLabel.getAllStyles().setFgColor(0xFF0000); // set the text color to red
                card.add(BorderLayout.SOUTH, closedLabel); // add the label to the container
            } else {
                card.add(BorderLayout.SOUTH, participateButton);

            }

            cards.add(card);
        }

        this.add(cards);

    }
}
