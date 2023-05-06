/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.genesisteam.maktabti.entities.Commentaire;
import com.genesisteam.maktabti.utilities.Statics;

import java.util.List;

/**
 *
 * @author SADOK
 */
public class CommentaireService {

    ConnectionRequest req;
    static CommentaireService instance = null;

    //util
    List<Commentaire> commentaires;
    Commentaire commentaire;

    //Constructor
    private CommentaireService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CommentaireService getInstance() {
        if (instance == null) {
            instance = new CommentaireService();
        }

        return instance;
    }

    public void ajoutCommentaire(Commentaire c) {
       

         String url =Statics.BASE_URL+"/evenements/commentaire/neww/"+c.getIdEvenement()+"?commentaire="+c.getCommentaire()+"&idClient="+c.getIdClient(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

    }
}
