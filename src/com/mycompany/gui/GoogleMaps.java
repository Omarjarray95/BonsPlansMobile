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
import static com.codename1.ui.events.ActionEvent.Type.Log;
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
        return new Coord(resa[0], reso[0]);
        }
        catch (IOException E)
        {
            System.out.println(E);
        }
        return null;
    }
    
    /*public static Coord SetPosition()
    {
        try
        {
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(true);
        r.setUrl("https://www.googleapis.com/geolocation/v1/geolocate");
        r.addArgument("key", "AIzaSyCemOIbHhipKFleIK8g1Jb6H-isTSbFFbI");
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String,Object> result;
        result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
        double[] resa = Result.fromContent(result).getAsDoubleArray("//lat");
        System.out.println(result);
        double[] reso = Result.fromContent(result).getAsDoubleArray("//lng");
        return new Coord(resa[0], reso[0]);
        }
        catch (IOException E)
        {
            System.out.println(E);
        }
        return null;
    }*/
    
    public GoogleMaps()
    {
        Form hi = new Form("Native Maps Test");
        hi.setLayout(new BorderLayout());
        cnt = new MapContainer("AIzaSyCeIRbinQ5bJ2h_Qk9i558DRTg9PranZQ0");
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
             if (i == 3)
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
    Style S = new Style();
    S.setBgTransparency(0xff);
    S.setBorder(Border.createLineBorder(1));
    S.setPaddingBottom(15);
    ac.setUnselectedStyle(S); ac.setSelectedStyle(S);
    Location position;
        try 
        {
            position = LocationManager.getLocationManager().getCurrentLocation();
            double La = position.getLatitude();
            double Lo = position.getLongitude();
            System.out.println(La + " " + Lo);
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
    
    //cnt.setCameraPosition(new Coord(-33.867, 151.206));
        /*Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(-33.867, 151.206));
        });
        
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        Button btnAddMarker = new Button("Add Marker");
        btnAddMarker.addActionListener(e->
        {
            cnt.setCameraPosition(new Coord(41.889, -87.622));
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> 
                     {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
        });

        Button btnAddPath = new Button("Add Path");
        btnAddPath.addActionListener(e->{

            cnt.addPath(
                    cnt.getCameraPosition(),
                    new Coord(-33.866, 151.195), // Sydney
                    new Coord(-18.142, 178.431),  // Fiji
                    new Coord(21.291, -157.821),  // Hawaii
                    new Coord(37.423, -122.091)  // Mountain View
            );
        });

        Button btnClearAll = new Button("Clear All");
        btnClearAll.addActionListener(e->{
            cnt.clearMapLayers();
        });

        cnt.addTapListener(e->{
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        enterName.getText(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                        }
                );
                dlg.dispose();
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });*/

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.north(ac));

        hi.add(BorderLayout.CENTER, root);
        hi.show();
    }
}
