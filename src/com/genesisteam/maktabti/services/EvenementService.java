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
import com.genesisteam.maktabti.entities.Commentaire;
import com.genesisteam.maktabti.entities.Evenement;
import com.genesisteam.maktabti.gui.SessionManager;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    Evenement evenement;
    List<Commentaire> commentaires;

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
                float idevenement = Float.parseFloat(item.get("idEvenement").toString());
                e.setIdEvenement((int) idevenement);

                e.setNom((String) item.get("nom"));
                e.setDescription((String) item.get("description"));
                e.setHeure((String) item.get("heure"));
                float nbticket = Float.parseFloat(item.get("nbticket").toString());
                e.setNbTicket((int) nbticket);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse((String) item.get("date"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                e.setDate(date);

                e.setImage((String) Statics.BASE_URL + "/photos/evenements/" + item.get("image"));
                e.setLieu((String) item.get("lieu"));

                evenements.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return evenements;
    }

    public Evenement getEvenement(int id) {
        req = new ConnectionRequest();

        // Build the API URL with the competition ID
        String fetchURL = Statics.BASE_URL + "/evenements/show/" + id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    evenement = fromJson(new String(req.getResponseData(), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.getMessage());
                }

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenement;
    }

    public Evenement fromJson(String jsonText) {
        JSONParser jp = new JSONParser();
        evenement = new Evenement();

        try {

            //2
            Map<String, Object> CompetitionsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) CompetitionsListJSON.get("root");
            for (Map<String, Object> item : list) {
                float idevenement = Float.parseFloat(item.get("idEvenement").toString());
                evenement.setIdEvenement((int) idevenement);

                evenement.setNom((String) item.get("nom"));
                evenement.setDescription((String) item.get("description"));
                evenement.setHeure((String) item.get("heure"));
                float nbticket = Float.parseFloat(item.get("nbticket").toString());
                evenement.setNbTicket((int) nbticket);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse((String) item.get("date"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                evenement.setDate(date);

                evenement.setImage((String) Statics.BASE_URL + "/photos/evenements/" + item.get("image"));
                evenement.setLieu((String) item.get("lieu"));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return evenement;
    }

    public interface EvenementCallback {

        void onSuccess(String message);

        void onError(String message);
    }

    public String fromJsonMessage(String jsonText) {
        JSONParser jp = new JSONParser();
        String message = "";
        try {
            Map<String, Object> responseJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (responseJSON.containsKey("message")) {
                message = (String) responseJSON.get("message");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return message;
    }

    public void participer(int id, String nbticket, EvenementCallback callback) {
        req = new ConnectionRequest();

        // Build the API URL with the competition ID
        String fetchURL = Statics.BASE_URL + "/evenements/ajouterreservation/rest/" + id + "/" + SessionManager.getId() + "?nbrTickets=" + nbticket;
        System.out.println(fetchURL);

        req.setUrl(fetchURL);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String message = fromJsonMessage(new String(req.getResponseData(), "UTF-8"));
                    if (message != null && message.startsWith("Reservation ajouter avec succés !")) {
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

    public List<Commentaire> getCommentsForEvenement(int idEvenement) {
        req = new ConnectionRequest();

        // Build the API URL with the event ID
        String fetchURL = Statics.BASE_URL + "/evenements/commentaire/get/" + idEvenement;

        req.setUrl(fetchURL);
        req.setPost(false);
        commentaires = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    commentaires = parseComments(new String(req.getResponseData(), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaires;
    }

    private List<Commentaire> parseComments(String jsonText) {
        // Parse the JSON response and create a list of Comment objects
        // based on the received data

        List<Commentaire> commentaires = new ArrayList<>();

        try {
            JSONParser jp = new JSONParser();
            Map<String, Object> commentsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) commentsListJSON.get("root");

            for (Map<String, Object> item : list) {
                Commentaire comment = new Commentaire();
                float idCommentaire = Float.parseFloat(item.get("idCommentaire").toString());
                comment.setIdCommentaire((int) idCommentaire);
                comment.setCommentaire((String) item.get("commentaire"));
                float idClient = Float.parseFloat(item.get("idClient").toString());
                comment.setIdClient((int)idClient);
                comment.setNomClient((String) item.get("nomClient"));

                // Add more attributes to the Comment object as needed
                commentaires.add(comment);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return commentaires;
    }

}
