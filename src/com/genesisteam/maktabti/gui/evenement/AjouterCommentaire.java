/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.evenement;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.genesisteam.maktabti.entities.Commentaire;
import com.genesisteam.maktabti.services.CommentaireService;

/**
 *
 * @author SADOK
 */
public class AjouterCommentaire extends Form {

    public AjouterCommentaire(int id) {

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

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

                Commentaire r = new Commentaire(14, id, commentTextArea.getText());
                //user connecté
                //SessionManager.getId()

                System.out.println("data commentaire = " + r);

                //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                CommentaireService.getInstance().ajoutCommentaire(r);
                // Clear the comment text area
                commentTextArea.setText("");

                // Show a success message
                Dialog.show("Success", "Commentaire ajouté avec succées", "OK", null);
            }
        });
        add(addButton);

    }

}
