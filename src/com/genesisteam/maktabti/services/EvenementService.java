/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author SADOK
 */
public class EvenementService {

    ConnectionRequest req;
    static EvenementService instance = null;

    //util
    List<Evenement> evenements;

    //Constructor
    private EvenementService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }

        return instance;
    }

    public List<Evenement> fetchEvenements() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/evenements/get";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }

    public List<Evenement> parseEvenements(String jsonText) {

        //var
        evenements = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> evenementsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenementsListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Evenement e = new Evenement();
                e.setIdEvenement((Double) item.get("idEvenement"));

                e.setNom((String) item.get("nom"));
                e.setDescription((String) item.get("description"));
                e.setHeure((String) item.get("heure"));
                e.setNbTicket((Double) item.get("nbticket"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date =  dateFormat.parse((String) item.get("date"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                e.setDate(date);

                e.setImage((String) item.get("image"));
                e.setLieu((String) item.get("lieu"));

                evenements.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return evenements;
    }

}
