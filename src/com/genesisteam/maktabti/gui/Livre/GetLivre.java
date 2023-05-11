/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.Livre;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.genesisteam.maktabti.entities.Livre;
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.gui.competition.CompetitionDetails;
import com.genesisteam.maktabti.services.CompetitionService;
import com.genesisteam.maktabti.services.LivreService;
import com.genesisteam.maktabti.services.QuestionService;
import com.genesisteam.maktabti.utilities.Statics;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Saleh
 */
public class GetLivre extends BaseForm {

    LivreService ls = LivreService.getInstance();

    private Resources theme;

    public GetLivre(Resources res) {

        setTitle("Liste des Livres");
        setScrollableY(true);
        super.addSideMenu(res);
  

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Livre l : ls.fetchlivre()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

          
            content.add(new Label(l.getTitre()));
            content.add(new Label(l.getIdAuteur()));
            content.add(new Label(l.getIdCategorie()));
            content.add(new Label("Prix: " + l.getPrix()));

            // create image
            //Image image = null;
            // EncodedImage enc = 
            /* try {
                image = URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                        l.getImage(),
                        l.getImage(),
                        URLImage.RESIZE_SCALE_TO_FILL
                );
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }*/
            // create image container and add image
            // Container imageContainer = new Container();
            //ImageViewer imgv = new ImageViewer(image);
            //imageContainer.add(imgv);
            // create button with icon
            Button detailsButton = new Button();
            FontImage.setMaterialIcon(detailsButton, FontImage.MATERIAL_INFO);
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Livre livre = ls.getlivre(l.getIdLivre());System.out.println(livre);
                    new LivreDetails(livre, res).show();
                }
            });
            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            //card.add(BorderLayout.WEST, imageContainer);
            card.add(BorderLayout.EAST, detailsButton);

         
              cards.add(card);

         
        }

        this.add(cards);
      

    }
}
