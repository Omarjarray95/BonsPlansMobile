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
import entities.Reclamation;

/**
 *
 * @author DADOU
 */
public class ServiceReclamation {
    
    public void ajoutReclamation(Reclamation rec) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/donia/web/app_dev.php/BonsPlans/AddRecWebSer/" + rec.getId_user()+"/"+rec.getSujet()
                +"/"+ rec.getContenu_rec()+ "/" + rec.getId_etab();
        con.setUrl(Url);

        System.out.println("Reclamation : id _etab"+ rec.getId_etab());
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    ArrayList<Reclamation> R = new ArrayList<Reclamation>();
     
    public int getRecParIdEtab(int id_etab)
    {       int nb=0;
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/donia/web/app_dev.php/BonsPlans/recidetabwebser/" + id_etab);  
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                R = getListReclamations(new String(con.getResponseData()));
            }
            
            });
        int i;
        for (i=0; i<=R.size(); i++ )
            {
               
               nb=nb+1;  
            }
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nb;
    }

    public ArrayList<Reclamation> getListReclamations(String json) {

        ArrayList<Reclamation> listReclamations = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> reclamations = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("reclamations");
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamations.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation rec = new Reclamation();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                
                System.out.println(id);
                rec.setId_rec((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                //rec.setId_user(obj.get("id_user"));
               rec.setSujet(obj.get("sujet").toString());
               //rec.setContenu_rec(obj.get("contenu_reclamation").toString());
               System.out.println(rec);
                listReclamations.add(rec);

            }

        } catch (IOException ex) {
        }
        System.out.println(listReclamations);
        return listReclamations;

    }
    
    
    ArrayList<Reclamation> listReclamations = new ArrayList<>();
    
    public ArrayList<Reclamation> getList2(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/donia/web/app_dev.php/BonsPlans/RecWebSer/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReclamation ser = new ServiceReclamation();
                listReclamations = ser.getListReclamations(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }


}
