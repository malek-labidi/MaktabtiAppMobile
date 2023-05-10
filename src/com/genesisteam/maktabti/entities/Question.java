/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.entities;

/**
 *
 * @author admin
 */
public class Question {
    private int idQuestion;
    private int idCompetition;
    private String question;
    private String choix1;
    private String choix2;
    private String choix3;
    private String reponseCorrect;

    public Question() {
    }

    public Question(int idQuestion, int idCompetition, String question, String choix1, String choix2, String choix3, String reponseCorrect) {
        this.idQuestion = idQuestion;
        this.idCompetition = idCompetition;
        this.question = question;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.reponseCorrect = reponseCorrect;
    }

    public Question(int idCompetition, String question, String choix1, String choix2, String choix3, String reponseCorrect) {
        this.idCompetition = idCompetition;
        this.question = question;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.reponseCorrect = reponseCorrect;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoix1() {
        return choix1;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public String getChoix2() {
        return choix2;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public String getChoix3() {
        return choix3;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public String getReponseCorrect() {
        return reponseCorrect;
    }

    public void setReponseCorrect(String reponseCorrect) {
        this.reponseCorrect = reponseCorrect;
    }

    @Override
    public String toString() {
        return "Question{" + "idQuestion=" + idQuestion + ", idCompetition=" + idCompetition + ", question=" + question + ", choix1=" + choix1 + ", choix2=" + choix2 + ", choix3=" + choix3 + ", reponseCorrect=" + reponseCorrect + '}';
    }
    
    
    
}
