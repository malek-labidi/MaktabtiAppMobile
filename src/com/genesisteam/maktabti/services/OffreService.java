/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.entities.Offre;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gaaloul
 */
public class OffreService {
    
    ConnectionRequest req;
    static OffreService instance = null;

    //util
    List<Offre> offres;
    Offre offre;

    //Constructor
    private OffreService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }

        return instance;
    }
    
    
    
    
    
     public List<Offre> fetchOffres() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/offre/get";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    } 
    
       
    
     public List<Offre> parseOffres(String jsonText) {

        //var
        offres = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> OffresListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) OffresListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Offre o = new Offre();
                float idoffre = Float.parseFloat(item.get("idOffre").toString());
                o.setId_offre((int)idoffre ); 
                float idlivre = Float.parseFloat(item.get("idLivre").toString());
                o.setId_livre((int)idlivre );
                float prixsolde = Float.parseFloat(item.get("prixSolde").toString());
                o.setId_livre((int)prixsolde );

                offres.add(o);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return offres;
    }

}
