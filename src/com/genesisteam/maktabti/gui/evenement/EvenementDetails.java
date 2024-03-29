/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.evenement;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.genesisteam.maktabti.entities.Commentaire;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.EvenementService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SADOK
 */
public class EvenementDetails extends BaseForm {
    EvenementService es = EvenementService.getInstance();
    List<Commentaire> commentaires ;

    public EvenementDetails(Evenement evenement,Resources res) {
        setTitle("Détails de l'Evenement");
        // add back button to toolbar
        Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

        Command back = new Command("", backIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ShowEvenement(res).showBack();
            }
        };
        getToolbar().addCommandToLeftBar(back);
        // widgets
        Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Image image = null;
        try {
            image = URLImage.createToStorage(
                    EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                    evenement.getImage(),
                    evenement.getImage(),
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
        content.add(new Label(evenement.getNom()));
        content.add(new Label(evenement.getDescription()));
        content.add(new Label(evenement.getDate().toString()));
        content.add(new Label(evenement.getLieu()));
        content.add(new Label(evenement.getHeure()));
        // Display comments
        SpanLabel titre = new SpanLabel("Commentaires");
        Container container = new Container(new BorderLayout());
container.add(BorderLayout.WEST, FontImage.createMaterial(FontImage.MATERIAL_MESSAGE, UIManager.getInstance().getComponentStyle("Label")));
container.add(BorderLayout.CENTER, titre);
content.add(container);
        commentaires =new ArrayList<>();
        commentaires= es.getCommentsForEvenement(evenement.getIdEvenement());
        
        for (Commentaire commentaire : commentaires) {
            content.add(new Label(commentaire.getNomClient()));
            content.add(new Label(commentaire.getCommentaire()));
            content.add(new Label("_________________________"));
        }
      
        Button addButton = new Button("Ajouter Commentaire");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                new AjouterCommentaire((int)evenement.getIdEvenement(),evenement,res).show();
            }
        });
        content.add(addButton);
        // add content to form
        this.add(content);

    }

}
