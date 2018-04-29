/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Etablissement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author omar
 */
public class EtablissementService 
{
    ArrayList<Etablissement> ListeEtablissements = new ArrayList<>();
    
    public ArrayList<Etablissement> getEtablissements(String json) 
    {
        ArrayList<Etablissement> LsE = new ArrayList<>();
        try 
        {
            JSONParser JP = new JSONParser();
            Map<String, Object> MapEtablissements = JP.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> LE = (List<Map<String, Object>>) MapEtablissements.get("root");

            for (Map<String, Object> obj : LE) 
            {
                Etablissement E = new Etablissement();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                E.setId((int) id);
                E.setNom(obj.get("nom").toString());
                E.setAdresse(obj.get("adresse").toString());
                E.setDescription(obj.get("description").toString());
                if (obj.get("typeResto") != null)
                {
                    E.setType_resto(obj.get("typeResto").toString());
                }
                if (obj.get("typeShops") != null)
                {
                    E.setType_shops(obj.get("typeShops").toString());
                }
                if (obj.get("typeLoisirs") != null)
                {
                    E.setType_loisirs(obj.get("typeLoisirs").toString());
                }
                if (obj.get("nbrStars") != null)
                {
                    E.setNbrStars(obj.get("nbrStars").toString());
                }
                E.setType(obj.get("type").toString());
                E.setHoraire_ouverture(obj.get("horaireOuverture").toString());
                E.setHoraire_fermeture(obj.get("horaireFermeture").toString());
                E.setNumtel(Integer.parseInt(obj.get("numTel").toString()));
                E.setImage(obj.get("imagePrincipale").toString());
                E.setUrl(obj.get("URL").toString());
                E.setBudgetmoyen(Integer.parseInt(obj.get("budgetmoyen").toString()));
                System.out.println(E);
                LsE.add(E);
            }

        } 
        catch (IOException ex) 
        {
            
        }
        System.out.println(LsE);
        return LsE;
    }
    
    public ArrayList<Etablissement> getEtablissementsParType(String type)
    {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/aptws/" + type);  
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                ListeEtablissements = getEtablissements(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeEtablissements;
    }
    
}
