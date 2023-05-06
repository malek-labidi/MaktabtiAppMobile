package com.genesisteam.maktabti.gui.evenement;

import com.genesisteam.maktabti.gui.evenement.EvenementDetails;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.util.Base64;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.services.EvenementService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;




public class ShowEvenement extends Form {
   

    
    EvenementService es = EvenementService.getInstance();
    
    public ShowEvenement() {
                 setTitle("Liste des Evenements");
        setScrollableY(true);
      
            Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

       Command back = new Command("Retour",backIcon) {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                new Home().showBack();
            } catch (IOException ex) {
                System.out.println("");
            }
        }
    };
         getToolbar().addCommandToLeftBar(back);
        

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
             Button detailsButton = new Button();
    FontImage.setMaterialIcon(detailsButton, FontImage.MATERIAL_INFO);
    detailsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Evenement evenement = es.getEvenement((int) e.getIdEvenement());
            new EvenementDetails(evenement).show();
        }
    });

            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            card.add(BorderLayout.WEST, imageContainer);
            card.add(BorderLayout.EAST, detailsButton);

            cards.add(card);
        }

        this.add(cards);

        }
}
