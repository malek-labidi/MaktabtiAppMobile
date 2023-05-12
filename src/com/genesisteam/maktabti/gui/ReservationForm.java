package com.genesisteam.maktabti.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.gui.evenement.ShowEvenement;
import com.genesisteam.maktabti.services.EvenementService;

public class ReservationForm extends BaseForm {

    EvenementService es = EvenementService.getInstance();
    private TextField ticketsField;

    public ReservationForm(Evenement ev, Resources res) {

        Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

        Command back = new Command("", backIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ShowEvenement(res).showBack();
            }
        };
        getToolbar().addCommandToLeftBar(back);
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Labels for event details
        SpanLabel nameLabel = new SpanLabel("Nom: " + ev.getNom());
        nameLabel.setUIID("ReservationLabel");
        add(nameLabel);

        SpanLabel dateLabel = new SpanLabel("Date: " + ev.getDate());
        dateLabel.setUIID("ReservationLabel");
        add(dateLabel);

        SpanLabel locationLabel = new SpanLabel("Lieu: " + ev.getLieu());
        locationLabel.setUIID("ReservationLabel");
        add(locationLabel);

        // Label for user information
        SpanLabel userLabel = new SpanLabel("Informations de Lutilisateur:");
        userLabel.setUIID("ReservationLabel");
        add(userLabel);

        // Disabled fields for user information
        TextField nameField = new TextField(SessionManager.getNom() + " " + SessionManager.getPrenom());
        nameField.setEnabled(false);
        addFieldWithLabel("Nom:", nameField);

        TextField emailField = new TextField(SessionManager.getEmail());
        emailField.setEnabled(false);
        addFieldWithLabel("Email:", emailField);

        // Tickets field
        ticketsField = new TextField();
        addFieldWithLabel("Nombre de tickets:", ticketsField);

        // Submit button
        Button submitButton = new Button("Valider");
        submitButton.addActionListener(e -> {
            String tickets = ticketsField.getText();
            es.participer(ev.getIdEvenement(), tickets, new EvenementService.EvenementCallback() {
                @Override
                public void onSuccess(String message) {
                    ToastBar.showMessage(message, FontImage.MATERIAL_CHECK);
                    new ShowEvenement(res).showBack();
                }

                @Override
                public void onError(String message) {
                    ToastBar.showMessage(message, FontImage.MATERIAL_WARNING);
                }
            });
        });
        add(submitButton);

        // Apply custom font to the labels
    }

    private void addFieldWithLabel(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setUIID("ReservationLabel");
        add(label);
        add(textField);
    }
}
