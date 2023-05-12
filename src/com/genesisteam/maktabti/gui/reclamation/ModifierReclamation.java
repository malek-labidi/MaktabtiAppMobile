package com.genesisteam.maktabti.gui.reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.genesisteam.maktabti.entities.Reclamation;
import com.genesisteam.maktabti.services.ReclamationService;

public class ModifierReclamation extends Form implements ActionListener {

    private final Reclamation reclamation;
    private final TextField messageField;
    private final TextField feedbackField;

    public ModifierReclamation(Reclamation reclamation) {
        super("Update Reclamation");
        this.reclamation = reclamation;
        this.messageField = new TextField(reclamation.getMessage(), "Message", 20, TextField.ANY);
        this.feedbackField = new TextField(reclamation.getFeedback(), "Feedback", 20, TextField.ANY);
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        this.addAll(messageField, feedbackField, submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String message = messageField.getText();
        String feedback = feedbackField.getText();
        ReclamationService.getInstance().modifierReclamation(reclamation.getIdReclamation(), message, feedback);
        Dialog.show("Success", "Reclamation updated successfully", "OK", null);
    }
}
