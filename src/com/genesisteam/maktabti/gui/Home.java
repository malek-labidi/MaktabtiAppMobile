/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.genesisteam.maktabti.entities.Livre;
import com.genesisteam.maktabti.gui.Livre.LivreDetails;
import com.genesisteam.maktabti.services.LivreService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.ir.Block;

/**
 *
 * @author admin
 */
public class Home extends BaseForm {

    Form current;
    private Resources theme;
    private EncodedImage enc;
    LivreService ls = LivreService.getInstance();
    List<Livre> livres;

    public Home(Resources res) {

        setScrollableY(true);
        // setUIID("HOMEPAGE");

        super.addSideMenu(res);

        try {
            enc = EncodedImage.create("/maktabti.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Image img=URLImage.createToStorage(enc,"http://localhost/img/shared.png","http://localhost/img/shared.png");
        ImageViewer imgv = new ImageViewer(enc);
        // add(imgv);

        Container c = new Container();
        c.add(imgv);
        c.add(new Container()); // empty container to separate image from label
        Label l = new Label("Dernieres Livres ");
        l.getAllStyles().setFgColor(0x000000);
        c.add(l);
        add(c);

        livres = new ArrayList<>();
        livres = ls.fetchlastlivre();
        Container cards = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Livre livre : ls.fetchlastlivre()) {
            // create card
            Container card = new Container(new BorderLayout());

            card.getAllStyles().setBgColor(0xFFFFFF);

            // create card content
            Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label title = new Label(livre.getTitre());
            title.getAllStyles().setFgColor(0x00377E); // set foreground color to red
            content.add(title);

            Label author = new Label(livre.getIdAuteur());
            author.getAllStyles().setFgColor(0xD4A373); // set foreground color to blue
            content.add(author);

            Label category = new Label(livre.getIdCategorie());
            category.getAllStyles().setFgColor(0xD4A373); // set foreground color to green
            content.add(category);

            Label price = new Label("Prix: " + livre.getPrix() + "DT");
            price.getAllStyles().setFgColor(0xD4A373); // set foreground color to black
            content.add(price);

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
                    Livre lastlivre = ls.getlivre(livre.getIdLivre());
                    System.out.println(livre);
                    new LivreDetails(lastlivre, res).show();
                }
            });
            // add content and image containers to card container
            card.add(BorderLayout.CENTER, content);
            //card.add(BorderLayout.WEST, imageContainer);
            card.add(BorderLayout.EAST, detailsButton);

            cards.add(card);

        }
        add(cards);

    }
}
