package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Log;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class GoogleMaps 
{
    Coord LL;
    final MapContainer cnt;
    String[] Adds;
    
    public String[] searchLocations(String text) 
    {
    try 
    {
        if(text.length() > 0) 
        {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key", "AIzaSyAAxYLLxmNPFJLibEfU5szcn1HCIp38HJI");
            r.addArgument("input", text);
            r.addArgument("components", "country:tn");
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result;
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            //System.out.println(result);
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            String[] resu = Result.fromContent(result).getAsStringArray("//place_id");
            LL = LongLat(resu[0]);
            cnt.setCameraPosition(LL);
            cnt.zoom(LL, 18);
            return res;
        }
    } 
    catch(Exception err) 
    {
        System.out.println(err);
    }
    return null;
    }
    
    public Coord LongLat(String PI)
    {
        try
        {
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        r.setUrl("https://maps.googleapis.com/maps/api/place/details/json");
        r.addArgument("key", "AIzaSyAAxYLLxmNPFJLibEfU5szcn1HCIp38HJI");
        r.addArgument("placeid", PI);
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String,Object> result;
        result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
        double[] resa = Result.fromContent(result).getAsDoubleArray("//geometry/location/lat");
        System.out.println(Result.fromContent(result).get("//geometry"));
        double[] reso = Result.fromContent(result).getAsDoubleArray("//geometry/location/lng");
        Adds = Result.fromContent(result).getAsStringArray("//formatted_address");
        return new Coord(resa[0], reso[0]);
        }
        catch (IOException E)
        {
            System.out.println(E);
        }
        return null;
    }
    
    public GoogleMaps(Coord LL)
    {
        Form hi = new Form("Google Maps");
        hi.setLayout(new BorderLayout());
        cnt = new MapContainer("AIzaSyCeIRbinQ5bJ2h_Qk9i558DRTg9PranZQ0");
        cnt.setCameraPosition(LL);
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField ac = new AutoCompleteTextField(options)
        {
        @Override
        protected boolean filter(String text) 
        {
         if(text.length() == 0) 
         {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) 
         {
             return false;
         }

         options.removeAll();
         int i = 1;
         for(String s : l) 
         {
             if (i == 2)
             {
                 break;
             }
             options.addItem(s);
             i++;
         }
         return true;
        }
    };
    ac.setMinimumElementsShownInPopup(3);
    ac.setHint("Précisez Votre Adresse ...");
    Style S = new Style();
    S.setBgTransparency(0xff);
    S.setBorder(Border.createLineBorder(1));
    S.setPaddingBottom(15);
    ac.setUnselectedStyle(S); ac.setSelectedStyle(S);
    Button BO = new Button("OK");
    BO.addActionListener(new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                UneDemande UD = new UneDemande();
                UD.getTF2().setText(Adds[0]);
                UD.getF().show();
            }
        });
    FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
    hi.getToolbar().addCommandToLeftBar("", icon, new ActionListener() 
    {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                UneDemande UD = new UneDemande();
                UD.getF().show();
            }
        });
    Container CN = new Container(new BoxLayout(BoxLayout.X_AXIS));
    CN.add(ac).add(BO);
    Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.north(CN));
    hi.add(BorderLayout.CENTER, root);
    hi.show();
    }
}
