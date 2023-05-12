/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.reclamation;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.genesisteam.maktabti.entities.Reclamation;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author Ilef
 */
public class ShowReclamation extends Form {

   public ShowReclamation(Form previous) {
    setTitle("List Reclamation");
    setLayout(BoxLayout.y());
    ArrayList<Reclamation> reclamations = ReclamationService.getInstance().getAllReclamations();
    
    
          
    for (Reclamation reclamation : reclamations) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);

        Label idLabel = new Label("ID: " + reclamation.getIdReclamation());
        Label feedbacktLabel = new Label("feedmback: " + reclamation.getFeedback());
        Label messageltLabel = new Label("message: " + reclamation.getMessage());


        idLabel.getStyle().setFgColor(0x000000);
        feedbacktLabel.getStyle().setFgColor(0x000000);
        messageltLabel.getStyle().setFgColor(0x000000);

   

        card.add(BorderLayout.NORTH, idLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(feedbacktLabel,messageltLabel));
        this.add(card);
                           Button btndelete = new Button("delete");


add(btndelete);

Button updateButton = new Button("Update Reclamation");
updateButton.addActionListener(e -> {
    ModifierReclamation updateForm = new ModifierReclamation(reclamation);
    updateForm.show();
    });

    
    


add(updateButton);
btndelete.addActionListener((e) -> {
      com.genesisteam.maktabti.services.ReclamationService.getInstance().suppReclamation(reclamation);
     ShowReclamation refresh = new ShowReclamation(previous);
     refresh.show();

});
    }

}
}
