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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.entities.Fidelite;
import com.genesisteam.maktabti.entities.Type;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gaaloul
 */
public class FideliteService {
       ConnectionRequest req;
    static FideliteService instance = null;

    //util
    List<Fidelite> fidelites;
    Fidelite fidelite;

    //Constructor
    private FideliteService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static FideliteService getInstance() {
        if (instance == null) {
            instance = new FideliteService();
        }

        return instance;
    }
    
    
   public Fidelite getFidelite(int id) {
        req = new ConnectionRequest();

        // Build the API URL with the competition ID
        String fetchURL = Statics.BASE_URL + "/fidelitee/get/" + id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    fidelite = fromJson(new String(req.getResponseData(), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.getMessage());
                }

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return fidelite;
    }

     public Fidelite fromJson(String jsonText) {
        JSONParser jp = new JSONParser();
        fidelite = new Fidelite();

        try {

            //2
            Map<String, Object> FidelitesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) FidelitesListJSON.get("root");
            for (Map<String, Object> item : list) {
                float idfidelite = Float.parseFloat(item.get("idFidelite").toString());
                fidelite.setId_fidelite((int)idfidelite );
                float total = Float.parseFloat(item.get("totalAchat").toString());
                fidelite.setTotal_achat((int)total );
                fidelite.setType(Type.valueOf((String) item.get("type")));
                
                float idclient = Float.parseFloat(item.get("idClient").toString());
                fidelite.setId_client((int)idclient );
                
               
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fidelite;
    }

}
