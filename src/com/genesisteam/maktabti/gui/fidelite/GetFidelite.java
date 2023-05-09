/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.gui.fidelite;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Fidelite;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.gui.SessionManager;
import com.genesisteam.maktabti.services.FideliteService;

/**
 *
 * @author Gaaloul
 */
public class GetFidelite extends BaseForm{
    FideliteService fs = FideliteService.getInstance();
    public GetFidelite(Resources res) {
        
        setScrollableY(true);
        super.addSideMenu(res);
        Fidelite f = fs.getFidelite(SessionManager.getId());
        Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
          Label Nom = new Label(SessionManager.getNom()+ " " + SessionManager.getPrenom());
    Nom.getUnselectedStyle().setFgColor(0x00377E); // définir la couleur de texte en rouge
    Nom.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 18)); 
    
         Label totalAchatLabel = new Label("Total d'achat : " + f.getTotal_achat());
    totalAchatLabel.getUnselectedStyle().setFgColor(0x00377E); // définir la couleur de texte en rouge
    totalAchatLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 18)); // définir une police de caractère en gras
    
    Label typeAchatLabel = new Label("Type d'achat : " + f.getType());
    typeAchatLabel.getUnselectedStyle().setFgColor(0x00377E); // définir la couleur de texte en bleu
    typeAchatLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 16)); 
        content.add(Nom);

    content.add(totalAchatLabel);
    content.add(typeAchatLabel);
         add(content);
        
    }
    
    
    
}
