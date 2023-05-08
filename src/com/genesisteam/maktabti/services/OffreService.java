/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.services;

import com.codename1.io.ConnectionRequest;
import com.genesisteam.maktabti.entities.Offre;
import java.util.List;

/**
 *
 * @author Gaaloul
 */
public class OffreService {
    
    ConnectionRequest req;
    static OffreService instance = null;

    //util
    List<Offre> offres;
    Offre offre;

    //Constructor
    private OffreService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }

        return instance;
    }
    
}
