/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.Livre;

import com.genesisteam.maktabti.gui.competition.GetCompetitions;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.entities.Livre;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.utilities.Statics;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author admin
 */
public class LivreDetails extends BaseForm {

    private Resources theme;

    public LivreDetails(Livre livre, Resources res) {
        setTitle("Détails de la livre");
        // add back button to toolbar
        Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

        Command back = new Command("", backIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new GetLivre(res).showBack();
            }
        };
        getToolbar().addCommandToLeftBar(back);

        // widgets
        Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        /*  Image image = null;
            // EncodedImage enc = 
            try {
               image = URLImage.createToStorage(
                        EncodedImage.createFromImage(Image.createImage("/load.png"), false),
                       // Livre.getImage(),
                       // livre.getImage(),
                        URLImage.RESIZE_SCALE_TO_FILL
                );    
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            

            // create image container and add image
            Container imageContainer = new Container();
            ImageViewer imgv = new ImageViewer(image);
            imageContainer.add(imgv);
            content.add(imageContainer);*/
        content.add(new Label("titre: " +livre.getTitre()));
        content.add(new Label("langue: " +livre.getLangue()));
        content.add(new Label("resume: " +livre.getResume()));
        content.add(new Label("AUTEUR: " +livre.getIdAuteur()));
        content.add(new Label("categorie: " +livre.getIdCategorie()));
        content.add(new Label("isbn: " + livre.getIsbn()));
        content.add(new Label("nombres des pages: " + livre.getNbPages()));
        content.add(new Label("date pub: " +livre.getDatePub().toString()));
        content.add(new Label("Prix: " + livre.getPrix()));
       
 
            //pdf
            Button pdf = new Button("obtenir fiche pdf");
            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdf.getUnselectedStyle()));
            pdf.addActionListener(m -> {
                try {
                    Document document = new Document();
                    
                    String outputPath = "file:///C:/xampp/pdff/livre" + livre.getIdLivre() + ".pdf";
                    PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

                    //        // Ajouter le logo  C:\xampp\htdocs
//       String logoPath = "file:///C:/xampp/htdocs/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
                    String currentDate = dateFormat.format(new Date());

                    document.open();
                    document.add(new Paragraph(livre.getDatePub().toString()));
                    document.add(new Paragraph("Langue: " + livre.getLangue()));
                    document.add(new Paragraph("Resume: " + livre.getResume()));
                    document.add(new Paragraph("Titre: " + livre.getTitre()));
                    document.add(new Paragraph("auteur: " + livre.getIdAuteur()));
                    document.add(new Paragraph("categorie: " + livre.getIdCategorie()));
                    document.add(new Paragraph("isbn: " + livre.getIsbn()));
                    document.add(new Paragraph("nombres des pages: " + livre.getNbPages()));
                    document.add(new Paragraph("Prix: " + livre.getPrix()));
                    

                    document.close();
                    Dialog.show("Enregistré", "", "", "OK");

                    Log.p("PDF file successfully created!");
                } catch (Exception e) {
                    Log.e(e);
                }
            });
            
            
            
            
            content.add(pdf);
        // add content to form
        this.add(content);

    }

}
