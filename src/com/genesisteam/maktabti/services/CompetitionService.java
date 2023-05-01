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
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
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
                c.setIdCompetition((Double) item.get("idCompetition"));
                c.setIdLivre((String) item.get("idLivre"));
                c.setNom((String) item.get("nom"));
                c.setLienCompetition((String) item.get("lienCompetition"));
                c.setListePaticipants((String) item.get("listePaticipants"));
                c.setRecompense((String) item.get("recompense"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDebut=null;
                try {
                    dateDebut = (Date) dateFormat.parse((String) item.get("dateDebut"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                c.setDateDebut( dateDebut);
                Date dateFin=null;
                try {
                    dateFin = (Date) dateFormat.parse((String) item.get("dateFin"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                c.setDateFin( dateFin);
                c.setImage((String) item.get("image"));

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

}
