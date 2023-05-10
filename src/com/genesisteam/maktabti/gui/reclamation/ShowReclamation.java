/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Reclamation;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.ReclamationService;

/**
 *
 * @author Ilef
 */
public class ShowReclamation extends BaseForm {

    private ReclamationService rs = ReclamationService.getInstance();

    public ShowReclamation(Resources res) {
        setTitle("Liste des Réclamations");
        setScrollableY(true);

        super.addSideMenu(res);

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Reclamation r : rs.getAllReclamations()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            content.add(new Label(r.getMessage()));
            content.add(new Label(r.getFeedback()));

            Button detailsButton = new Button();
            FontImage.setMaterialIcon(detailsButton, FontImage.MATERIAL_INFO);
//            detailsButton.addActionListener(new ActionListener() {
//                @Override
////                public void actionPerformed(ActionEvent evt) {
////                    Reclamation reclamation = rs.getAllReclamations((int) r.getIdReclamation());
////                    new ReclamationDetails(reclamation, res).show();
////                }
//            });

            // add content to card container
            card.add(BorderLayout.CENTER, content);
            card.add(BorderLayout.EAST, detailsButton);

            cards.add(card);
        }

        this.add(cards);

    }
}
