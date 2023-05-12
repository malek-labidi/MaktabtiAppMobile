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
import com.genesisteam.maktabti.entities.Competition;
import com.genesisteam.maktabti.gui.SessionManager;
import com.genesisteam.maktabti.utilities.Statics;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class CompetitionService {

    ConnectionRequest req;
    static CompetitionService instance = null;

    //util
    boolean resultOK = false;
    List<Competition> competitions;
    Competition competition;
    String message;

    //Constructor
    private CompetitionService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CompetitionService getInstance() {
        if (instance == null) {
            instance = new CompetitionService();
        }

        return instance;
    }

    public List<Competition> parseCompetitions(String jsonText) {

        //var
        competitions = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> CompetitionsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) CompetitionsListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Competition c = new Competition();
                float idCompetition = Float.parseFloat(item.get("idCompetition").toString());
                c.setIdCompetition((int) idCompetition);
                c.setIdLivre((String) item.get("idLivre"));
                c.setNom((String) item.get("nom"));
                c.setLienCompetition((String) item.get("lienCompetition"));
                c.setListePaticipants((String) item.get("listePaticipants"));
                c.setRecompense((String) item.get("recompense"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDebut = null;
                try {
                    dateDebut = (Date) dateFormat.parse((String) item.get("dateDebut"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                c.setDateDebut(dateDebut);
                Date dateFin = null;
                try {
                    dateFin = (Date) dateFormat.parse((String) item.get("dateFin"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                c.setDateFin(dateFin);
                c.setImage((String) Statics.BASE_URL + "/photos/competitions/" + item.get("image"));

                competitions.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return competitions;
    }

    public List<Competition> fetchCompetitions() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/competitions/get";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                competitions = parseCompetitions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return competitions;
    }

    public Competition getCompetition(int id) {
        req = new ConnectionRequest();

        // Build the API URL with the competition ID
        String fetchURL = Statics.BASE_URL + "/competitions/get/" + id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    competition = fromJson(new String(req.getResponseData(), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.getMessage());
                }

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return competition;
    }

    public Competition fromJson(String jsonText) {
        JSONParser jp = new JSONParser();
        competition = new Competition();

        try {

            //2
            Map<String, Object> CompetitionsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) CompetitionsListJSON.get("root");
            for (Map<String, Object> item : list) {
                float idCompetition = Float.parseFloat(item.get("idCompetition").toString());
                competition.setIdCompetition((int) idCompetition);
                competition.setIdLivre((String) item.get("idLivre"));
                competition.setNom((String) item.get("nom"));
                competition.setLienCompetition((String) item.get("lienCompetition"));
                competition.setListePaticipants((String) item.get("listePaticipants"));
                competition.setRecompense((String) item.get("recompense"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDebut = null;
                try {
                    dateDebut = (Date) dateFormat.parse((String) item.get("dateDebut"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                competition.setDateDebut(dateDebut);
                Date dateFin = null;
                try {
                    dateFin = (Date) dateFormat.parse((String) item.get("dateFin"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                competition.setDateFin(dateFin);
                competition.setImage((String) Statics.BASE_URL + "/photos/competitions/" + item.get("image"));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return competition;
    }

   public interface ParticiperCallback {
    void onSuccess(String message);
    void onError(String message);
}

public void participer(int id, String reponses,ParticiperCallback callback) {
    req = new ConnectionRequest();

    // Build the API URL with the competition ID
    String fetchURL = Statics.BASE_URL + "/competitions/participer/rest/" + id+"/"+SessionManager.getId()+"?"+reponses;
    System.out.println(fetchURL);

    req.setUrl(fetchURL);

    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                String message = fromJsonMessage(new String(req.getResponseData(), "UTF-8"));
                if (message != null && message.startsWith("Votre participation est enregistrée avec succès !")) {
                    callback.onSuccess(message);
                } else {
                    callback.onError(message);
                }
            } catch (UnsupportedEncodingException ex) {
                callback.onError(ex.getMessage());
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}

public String fromJsonMessage(String jsonText) {
    JSONParser jp = new JSONParser();
    String message = "";
    try {

        Map<String, Object> CompetitionsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          if (CompetitionsListJSON.containsKey("message")) {
            message = (String) CompetitionsListJSON.get("message");
        }
        

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return message;
}

}
