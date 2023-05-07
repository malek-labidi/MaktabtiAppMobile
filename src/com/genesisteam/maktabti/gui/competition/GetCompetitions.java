/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.competition;

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
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.services.CompetitionService;
import com.genesisteam.maktabti.services.QuestionService;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author admin
 */
public class GetCompetitions extends BaseForm {

    CompetitionService cs = CompetitionService.getInstance();
    QuestionService qs = QuestionService.getInstance();
    private Resources theme;

    public GetCompetitions(Resources res) {

        setTitle("Liste des Compétitions");
        setScrollableY(true);
        super.addSideMenu(res);
        /*Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

        Command back = new Command("", backIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    new Home().showBack();
                } catch (IOException ex) {
                    System.out.println("");
                }
            }
        };
        getToolbar().addCommandToLeftBar(back);*/

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Competition c : cs.fetchCompetitions()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            content.add(new Label(c.getNom()));
            //content.add(new Label(c.getRecompense()));
            content.add(new Label(c.getDateDebut().toString()));
            content.add(new Label(c.getDateFin().toString()));

            // create image
            Image image = null;
            // EncodedImage enc = 
            try {
                image = URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                        Statics.BASE_URL+"/photos/competitions/"+c.getImage(),
                        Statics.BASE_URL+"/photos/competitions/"+c.getImage(),
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
                    new CompetitionDetails(competition,res).show();
                }
            });

            Button participateButton = new Button("Participer");
            participateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<Question> questions = qs.fetchQuestions(c.getIdCompetition());
                    new getQuestions(questions,res).show();
                }
            });

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            card.add(BorderLayout.WEST, imageContainer);
            card.add(BorderLayout.EAST, detailsButton);
            card.add(BorderLayout.SOUTH, participateButton);

            cards.add(card);
        }

        this.add(cards);

    }
}
