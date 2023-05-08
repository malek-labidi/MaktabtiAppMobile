/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.genesisteam.maktabti.services;

import com.codename1.io.ConnectionRequest;
import com.genesisteam.maktabti.entities.Fidelite;
import java.util.List;

/**
 *
 * @author Gaaloul
 */
public class FideliteService {
       ConnectionRequest req;
    static FideliteService instance = null;

    //util
    List<Fidelite> fidelites;
    Fidelite fidelite;

    //Constructor
    private FideliteService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static FideliteService getInstance() {
        if (instance == null) {
            instance = new FideliteService();
        }

        return instance;
    }
    
    
}
