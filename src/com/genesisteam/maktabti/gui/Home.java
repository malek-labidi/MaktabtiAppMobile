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
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.codename1.ui.EncodedImage;
import java.io.IOException;



/**
 *
 * @author wassi
 */
public class Home extends BaseForm {

    Form current;
    private Resources theme;
    private EncodedImage enc;

    public Home(Resources res) {

    
        setScrollableY(true);
        setUIID("HOMEPAGE");
        try {
            setBgImage(EncodedImage.create("/home-img.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       super.addSideMenu(res);
         

        try {
            enc = EncodedImage.create("/maktabti.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Image img=URLImage.createToStorage(enc,"http://localhost/img/shared.png","http://localhost/img/shared.png");
        ImageViewer imgv = new ImageViewer(enc);
        add(imgv);
    }
}
