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
import com.genesisteam.maktabti.entities.Livre;
import static com.genesisteam.maktabti.services.CompetitionService.instance;
import com.genesisteam.maktabti.utilities.Statics;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Saleh
 */
public class LivreService {
    ConnectionRequest req;
    static LivreService instance = null;

    //util
    boolean resultOK = false;
    List<Livre> livres;
    Livre livre;

    //Constructor
    private LivreService() {
        req = new ConnectionRequest();
    }

    //Singleton
     public static LivreService getInstance() {
        if (instance == null) {
            instance = new LivreService();
        }

        return instance;
    }

    public List<Livre> parselivre(String jsonText) {

        //var
        livres = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> LivreListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) LivreListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {
                System.out.println(item);

                Livre l = new Livre();
                float idLivre = Float.parseFloat(item.get("idLivre").toString());
                l.setIdLivre((int) idLivre);
                l.setIdAuteur((String) item.get("idAuteur"));
                l.setIdCategorie((String) item.get("idcategorie"));
                l.setTitre((String) item.get("titre"));
                l.setLangue((String) item.get("langue"));
                float isbn = Float.parseFloat(item.get("isbn").toString());
                l.setIsbn((int) isbn);
                float nbPages = Float.parseFloat(item.get("nbPages").toString());
                l.setNbPages((int) nbPages);
                l.setResume((String) item.get("resume"));
                float prix = Float.parseFloat(item.get("prix").toString());
                l.setPrix((int) prix);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datePub = null;
                try {
                    datePub = (Date) dateFormat.parse((String) item.get("datePub"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                l.setDatePub(datePub);
               
               // c.setImage((String) Statics.BASE_URL + "/photos/competitions/" + item.get("image"));

                livres.add(l);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return livres;
    }

    public List<Livre> fetchlivre() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/livres/get";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livres = parselivre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return livres;
    }

    public Livre getlivre(int id) {
        req = new ConnectionRequest();

        // Build the API URL with the competition ID
        String fetchURL = Statics.BASE_URL + "/livres/get/" + id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    livre = fromJson(new String(req.getResponseData(), "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex.getMessage());
                }

                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return livre;
    }

    public Livre fromJson(String jsonText) {
        JSONParser jp = new JSONParser();
        livre = new Livre();

        try {

            //2
            Map<String, Object> LivreListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) LivreListJSON.get("root");
            for (Map<String, Object> item : list) {

                 
                float idLivre = Float.parseFloat(item.get("idLivre").toString());
                livre.setIdLivre((int) idLivre);
                livre.setIdAuteur((String) item.get("idAuteur"));
                livre.setIdCategorie((String) item.get("idcategorie"));
                livre.setTitre((String) item.get("titre"));
                livre.setLangue((String) item.get("langue"));
                float isbn = Float.parseFloat(item.get("isbn").toString());
                livre.setIsbn((int) isbn);
                 float nbPages = Float.parseFloat(item.get("nbPages").toString());
                livre.setNbPages((int) nbPages);
                livre.setResume((String) item.get("resume"));
                float prix = Float.parseFloat(item.get("prix").toString());
                livre.setPrix((int) prix);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datePub = null;
                try {
                    datePub = (Date) dateFormat.parse((String) item.get("datePub"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                livre.setDatePub(datePub);
               
               // c.setImage((String) Statics.BASE_URL + "/photos/competitions/" + item.get("image"));

                livres.add(livre);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return livre;
    }
    
    
    
    public List<Livre> parselastlivre(String jsonText) {

        //var
        livres = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> LivreListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) LivreListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {
                System.out.println(item);

                Livre l = new Livre();
                float idLivre = Float.parseFloat(item.get("idLivre").toString());
                l.setIdLivre((int) idLivre);
                l.setIdAuteur((String) item.get("idAuteur"));
                l.setIdCategorie((String) item.get("idcategorie"));
                l.setTitre((String) item.get("titre"));
                l.setLangue((String) item.get("langue"));
                float isbn = Float.parseFloat(item.get("isbn").toString());
                l.setIsbn((int) isbn);
                float nbPages = Float.parseFloat(item.get("nbPages").toString());
                l.setNbPages((int) nbPages);
                l.setResume((String) item.get("resume"));
                float prix = Float.parseFloat(item.get("prix").toString());
                l.setPrix((int) prix);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datePub = null;
                try {
                    datePub = (Date) dateFormat.parse((String) item.get("datePub"));
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                l.setDatePub(datePub);
               
               // c.setImage((String) Statics.BASE_URL + "/photos/competitions/" + item.get("image"));

                livres.add(l);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return livres;
    }

    public List<Livre> fetchlastlivre() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/livres/lastlivres";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livres = parselivre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return livres;
    }

}
    

