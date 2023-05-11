/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.gui.offre;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.gui.competition.CompetitionDetails;
import com.genesisteam.maktabti.gui.competition.getQuestions;
import com.genesisteam.maktabti.services.CompetitionService;
import com.genesisteam.maktabti.services.OffreService;
import com.genesisteam.maktabti.services.QuestionService;
import java.io.IOException;
import java.util.List;
import com.genesisteam.maktabti.entities.Offre;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.gui.evenement.EvenementDetails;
import com.genesisteam.maktabti.services.EvenementService;

/**
 *
 * @author Gaaloul
 */
public class GetOffre extends BaseForm{
      /* OffreService os = OffreService.getInstance();

    public GetOffre(Resources res) {
        setTitle("Liste des Offres");
        setScrollableY(true);

        super.addSideMenu(res);

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Offre e : os.fetchOffres()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            content.add(new Label(e.getPourcentage_solde()));
          

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            

            cards.add(card);
        }

        this.add(cards);

    }*/
}
