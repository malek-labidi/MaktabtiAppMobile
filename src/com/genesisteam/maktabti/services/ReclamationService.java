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
import com.codename1.ui.events.ActionListener;
import com.genesisteam.maktabti.entities.Reclamation;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;


/**
 *
 * @author Ilef
 */
public class ReclamationService {
      public static ReclamationService instance = null;
   public boolean resultOK;

    
    private ConnectionRequest req;
         ArrayList<Reclamation> reclamations;

    
    public static ReclamationService getInstance(){
        if (instance == null){
            instance = new ReclamationService();
        }
        return instance;
    }
    public ReclamationService(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterReclamation(Reclamation r){
        String url = Statics.BASE_URL+"/ajoutMobile?message="+ r.getMessage()+"&feedback="+r.getFeedback();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Reclamation> parseReclamation(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("idReclamation").toString());
                r.setIdReclamation((int) id);
                r.setMessage((String)obj.get("message"));
                r.setFeedback((String)obj.get("feedback"));

                
               

               reclamations.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASE_URL + "/afficheMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamation(new String(req.getResponseData()));

                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  reclamations;
    }
    
public boolean suppReclamation(Reclamation t)
    {

         String url = Statics.BASE_URL + "/deleteMobile?id="+t.getIdReclamation();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;



    }
         
public void modifierReclamation(int id, String message, String feedback) {
    String url = Statics.BASE_URL + "/updateMobile?id=" + id + "&message=" +message+"&feedback=" + feedback;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("PUT");
    System.out.println(url);
    req.addResponseListener((NetworkEvent evt) -> {
        if (req.getResponseCode() == 200) {
            JSONParser parser = new JSONParser();
            try {
                Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println(response);
            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}

}
