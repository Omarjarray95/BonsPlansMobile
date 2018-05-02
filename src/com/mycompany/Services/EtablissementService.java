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
import com.codename1.ui.html.HTMLUtils;
import com.codename1.util.StringUtil;
import entities.Etablissement;
import entities.Reclamation;
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
    
   
    ArrayList<Etablissement> listEtab = new ArrayList<>();
    
    public ArrayList<Etablissement> getListEtab(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/donia/web/app_dev.php/BonsPlans/EtabsWebSer");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EtablissementService ser = new EtablissementService();
                listEtab = ser.getEtablissements(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEtab;
    }
    
    
    public Etablissement getEtablissementsParNom(String nom)
    {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/donia/web/app_dev.php/BonsPlans/EtabRechWebSer/" + nom);  
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                ListeEtablissements = getEtablissements(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeEtablissements.get(0);
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
    
    public void ModifierEtablissement(Etablissement E) 
    {
        ConnectionRequest con = new ConnectionRequest();
        String D0 = "";
        if (E.getType_resto() != null)
        {
            D0 = E.getType_resto();
        }
        if (E.getType_shops() != null)
        {
            D0 = E.getType_shops();
        }
        if (E.getType_loisirs() != null)
        {
            D0 = E.getType_loisirs();
        }
        if (E.getNbrStars() != null)
        {
            D0 = E.getNbrStars();
        }
        String Desc = StringUtil.replaceAll(E.getDescription(), "à", "a");
        String Nom = StringUtil.replaceAll(E.getNom(), "à", "a");
        String D0A = StringUtil.replaceAll(D0, "à", "a");
        String Adresse = StringUtil.replaceAll(E.getAdresse(), "à", "a");
        String Site = StringUtil.replaceAll(E.getUrl(), "à", "a");
        String DescA = StringUtil.replaceAll(Desc, "&", "And");
        String NomA = StringUtil.replaceAll(Nom, "&", "And");
        String D0AA = StringUtil.replaceAll(D0A, "&", "And");
        String AdresseA = StringUtil.replaceAll(Adresse, "&", "And");
        String SiteA = StringUtil.replaceAll(Site, "&", "And");
        String Url = "http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/miews?nom=" + NomA + 
        "&type=" + E.getType() + "&type1=" + D0AA + "&adresse=" + AdresseA + "&description=" + DescA +
        "&horaireouverture=" + E.getHoraire_ouverture() + "&horairefermeture=" + E.getHoraire_fermeture() + 
        "&numero=" + Integer.toString(E.getNumtel()) + "&image=" + E.getImage() + "&url=" + SiteA + "&budgetmoyen=" +
        Integer.toString(E.getBudgetmoyen()) + "&id=" + Integer.toString(E.getId());
        con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> 
        {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void SupprimerEtablissement(Etablissement E)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/sews/" + Integer.toString(E.getId())); 
        con.addResponseListener((e) -> 
        {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
