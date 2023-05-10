/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.evenement;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Commentaire;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.CommentaireService;

/**
 *
 * @author SADOK
 */
public class AjouterCommentaire extends BaseForm {

    public AjouterCommentaire(int id,Evenement ev ,Resources res) {

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

      Command back = new Command("",backIcon) {
        @Override
        public void actionPerformed(ActionEvent evt) {
            new EvenementDetails(ev, res).showBack();
        }
    };
    getToolbar().addCommandToLeftBar(back);

// Create a text area for entering the comment
        TextArea commentTextArea = new TextArea();
        commentTextArea.setHint("Entrez un commentaire...");
        add(commentTextArea);

// Create a button for adding the comment
        Button addButton = new Button("Ajouter");
        addButton.addActionListener(e -> {
            String comment = commentTextArea.getText();
            // Validate the comment
            if (comment.isEmpty()) {
                Dialog.show("Error", "Entrez un commentaire", "OK", null);
            } else {
                // Add the comment to the database or perform other operations

                Commentaire r = new Commentaire(id, commentTextArea.getText());
                

                System.out.println("data commentaire = " + r);

                CommentaireService.getInstance().ajoutCommentaire(r);
                // Clear the comment text area
                commentTextArea.setText("");

                // Show a success message
                Dialog.show("Success", "Commentaire ajouté avec succées", "OK", null);
                new EvenementDetails(ev, res).showBack();
            }
        });
        add(addButton);

    }

}
