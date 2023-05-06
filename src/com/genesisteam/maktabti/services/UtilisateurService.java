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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.gui.Login;
import com.genesisteam.maktabti.gui.SessionManager;
import java.util.Map;
import com.genesisteam.maktabti.entities.Utilisateur;
import com.genesisteam.maktabti.utilities.Statics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wassim
 */
public class UtilisateurService {
      //singleton 
    public static UtilisateurService instance = null ;
    private Resources theme;
    public  boolean resultOk;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UtilisateurService getInstance() {
        if(instance == null )
            instance = new UtilisateurService();
        return instance ;
    }
    
    
    
    public UtilisateurService() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom,TextField prenom,TextField email,TextField password ,TextField Numerotelephone,TextField Role , Resources res ) {
        
     
        
        String url = "http://localhost:8000/register_rest?Nom="+nom.getText().toString()+"&Prenom="+prenom.getText().toString()+
                "&Email="+email.getText().toString()+"&Mot_de_passe="+password.getText().toString()+"&Num_telephone="+Numerotelephone.getText().toString()+"&Role="+Role.getText().toString();
        
        req.setUrl(url);
               req.setPost(false);
        //Control saisi
        if(nom.getText().equals(" ") && prenom.getText().equals(" ") && email.getText().equals(" ")&& password.getText().equals(" ")&& Numerotelephone.getText().equals(" ")&& Role.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
//    SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = "http://localhost:8000/login_rest?email="+email.getText().toString()+"&mot_de_passe="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
               float id = Float.parseFloat(user.get("id").toString());
               SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
               SessionManager.setNom(user.get("nom").toString());
               SessionManager.setprenom(user.get("prenom").toString());
               SessionManager.setEmail(user.get("email").toString());
               SessionManager.setMot_de_passe(user.get("mot_de_passe").toString());
               float numtel = Float.parseFloat(user.get("num_telephone").toString());
               SessionManager.setNum_telephone((int)numtel);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
               SessionManager.setRole(user.get("role").toString());

                System.out.println("current user"+SessionManager.getEmail()+","+SessionManager.getMot_de_passe());
          
                if(user.size() >0 ) // l9a user
                    new Home(theme).show();
                    System.out.println("welcome");
                    
                }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
       /*public boolean addDestination(Utilisateur user) {
           
        String url = Statics.BASE_URL + "/register_rest?nom="+user.getNom()+"&prenom="+user.getPrenom()+
                "&email="+user.getEmail()+"&mot_de_passe="+user.getMot_de_passe()+
                "&num_telephone="+user.getNum_telephone()+"&role="+user.getRole();
        

        req.setUrl(url);
        req.setPost(false);

       req.addArgument("nom", user.getNom());
       req.addArgument("prenom", user.getPrenom());
       req.addArgument("email", user.getEmail());
       req.addArgument("mot_de_passe", user.getMot_de_passe());
       req.addArgument("num_telephone", String.valueOf(user.getNum_telephone()));
       req.addArgument("role", user.getRole());

  
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
   }*/
}
