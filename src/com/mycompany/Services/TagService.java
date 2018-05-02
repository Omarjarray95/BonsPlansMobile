package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Etablissement;
import entities.Tag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagService 
{
    public void AjouterTag(Etablissement E, Tag T) 
    {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/atews/" + Integer.toString(E.getId()) 
        + "?tag=" + T.getNom();
        con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> 
        {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    ArrayList<Tag> ListeTags = new ArrayList<>();
    
    public ArrayList<Tag> getTags(String json) 
    {
        ArrayList<Tag> LsT = new ArrayList<>();
        try 
        {
            JSONParser JP = new JSONParser();
            Map<String, Object> MapTags = JP.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> LT = (List<Map<String, Object>>) MapTags.get("root");

            for (Map<String, Object> obj : LT) 
            {
                Tag T = new Tag();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                T.setId((int) id);
                T.setNom(obj.get("name").toString());
                LsT.add(T);
            }

        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
        System.out.println(LsT);
        return LsT;
    }
    
    public ArrayList<Tag> getTagsNonExistants(String type)
    {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Bons_Plans/web/app_dev.php/BonsPlans/atnews/" + type);  
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                ListeTags = getTags(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ListeTags;
    }
}
