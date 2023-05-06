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
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class QuestionService {
    
      ConnectionRequest req;
    static QuestionService instance = null;

    //util
    boolean resultOK = false;
    List<Question> questions;
    Question question;

    //Constructor
    private QuestionService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }

        return instance;
    }
    
    
    public List<Question> parseQuestions(String jsonText) {

        //var
        questions = new ArrayList<>();

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

                Question c = new Question();
                c.setIdCompetition((Double) item.get("idCompetition"));
                c.setIdQuestion((Double) item.get("idQuestion"));
                c.setQuestion((String) item.get("question"));
                c.setChoix1((String) item.get("choix1"));
                c.setChoix2((String) item.get("choix2"));
                c.setChoix3((String) item.get("choix3"));
                c.setReponseCorrect((String) item.get("reponseCorrect"));
                

                questions.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return questions;
    }
    
    
    public List<Question> fetchQuestions(Double id) {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/competitions/quiz/get/"+id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                questions = parseQuestions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return questions;
    }
    
    
    
}
