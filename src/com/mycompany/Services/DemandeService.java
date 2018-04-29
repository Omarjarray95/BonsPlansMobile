package com.mycompany.Services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import entities.Demande;

public class DemandeService 
{
    public void AjouterDemande(Demande D) 
    {
        ConnectionRequest con = new ConnectionRequest();
        String D0 = "";
        if (D.getType_resto() != null)
        {
            D0 = D.getType_resto();
        }
        if (D.getType_shops() != null)
        {
            D0 = D.getType_shops();
        }
        if (D.getType_loisirs() != null)
        {
            D0 = D.getType_loisirs();
        }
        if (D.getNbrStars() != null)
        {
            D0 = D.getNbrStars();
        }
        String Url = "http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/adaews?nom=" + D.getNom() + 
        "&type=" + D.getType() + "&type1=" + D0 + "&adresse=" + D.getAdresse() + "&description=" + D.getDescription() +
        "&horaireouverture=" + D.getHoraire_ouverture() + "&horairefermeture=" + D.getHoraire_fermeture() + 
        "&numero=" + Integer.toString(D.getNumtel()) + "&image=" + D.getImage() + "&url=" + D.getUrl() + "&budgetmoyen=" +
        Integer.toString(D.getBudgetmoyen());
        con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> 
        {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
