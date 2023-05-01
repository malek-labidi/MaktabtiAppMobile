package com.genesisteam.maktabti.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.util.Base64;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.services.EvenementService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;




public class ShowEvenement extends Form {
    
    EvenementService es = EvenementService.getInstance();
    
    public ShowEvenement() {
         setTitle("Liste des Evenements");
        setScrollableY(true);
        getToolbar().addCommandToRightBar("Retour", null, ev->{
            try {
                new Home().show();
            } catch (IOException ex) {
                System.out.println("");
            }
        });

        // widgets
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Evenement e : es.fetchEvenements()) {
            // create card
            Container card = new Container(new BorderLayout());

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            content.add(new Label(e.getNom()));
            content.add(new Label(e.getDescription()));
            content.add(new Label(e.getDate().toString()));
            content.add(new Label(e.getLieu()));

           
              Image image = null;
            // EncodedImage enc = 
            try {
                image =  URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                        e.getImage(),
                        e.getImage(),
                        URLImage.RESIZE_SCALE_TO_FILL
                );      
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            

            // create image container and add image
            Container imageContainer = new Container();
            ImageViewer imgv = new ImageViewer(image);
            imageContainer.add(imgv);

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            card.add(BorderLayout.WEST, imageContainer);

            cards.add(card);
        }

        this.add(cards);
    }
}
