/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Reclamation;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.services.ReclamationService;

/**
 *
 * @author Ilef
 */
public class AjouterReclamation extends BaseForm{
      public AjouterReclamation(int id,Resources res) {

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
 super.addSideMenu(res);
// Create a text area for entering the comment
        TextArea messageTextArea = new TextArea();
        messageTextArea.setHint("Entrez une message...");
        add(messageTextArea);
          TextArea feedbackTextArea = new TextArea();
        feedbackTextArea.setHint("Entrez un feedback...");
        add(feedbackTextArea);

// Create a button for adding the comment
        Button addButton = new Button("Ajouter");
          

        
        addButton.addActionListener(e -> {
            String message = messageTextArea.getText();
            String feedback = feedbackTextArea.getText();

            // Validate the comment
            if (message.isEmpty() |feedback.isEmpty() ) {
                Dialog.show("Error", "Entrez un reclamation", "OK", null);
            } else {
                // Add the comment to the database or perform other operations

                  Reclamation r = new Reclamation(messageTextArea.getText(),feedbackTextArea.getText());
//                //user connecté
//                //SessionManager.getId()
//
//                System.out.println("data reclamation = " + r);

                //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                ReclamationService.getInstance().AjouterReclamation(r);
                // Clear the comment text area
                messageTextArea.setText("");
                feedbackTextArea.setText("");

                // Show a success message
                Dialog.show("Success", "Reclamation ajouté avec succées", "OK", null);
            }
        });

          

        

        add(addButton);

    }
    
}
