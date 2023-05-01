package com.genesisteam.maktabti.gui;

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
import java.io.UnsupportedEncodingException;




public class ShowEvenement extends Form {
    
    EvenementService es = EvenementService.getInstance();
    
    public ShowEvenement() {

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

            // decode base64-encoded image data and create an EncodedImage
            /*EncodedImage encodedImage = null;
            byte[] imageData = null;
            try {
                imageData = Base64.decode(e.getImage().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex.getMessage());
            }
            encodedImage = EncodedImage.create(imageData);

            // create image container and add image
            Container imageContainer = new Container();
            imageContainer.add(URLImage.createToStorage(encodedImage, e.getNom() + "_image", e.getImage()));*/

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
           // card.add(BorderLayout.WEST, imageContainer);

            cards.add(card);
        }

        this.add(cards);
    }
}
