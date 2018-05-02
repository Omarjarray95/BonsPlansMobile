package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Etablissement;
import java.io.IOException;
import java.util.ArrayList;

public class Recherches 
{
    private ArrayList<Etablissement> ALE;
    private Form F;
    private Container C;
    
    public Recherches(ArrayList<Etablissement> ALE)
    {
        this.ALE = ALE;
        F = new Form("Nos Etablissements", new BoxLayout(BoxLayout.Y_AXIS));
        C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Hamburger H = new Hamburger();
        H.CreateHams(F);
        TextField TF0 = new TextField();
        /*TF0.addDataChangedListener(new DataChangedListener() 
        {
            @Override
            public void dataChanged(int type, int index) 
            {
                ArrayList<Etablissement> ALE1 = new ArrayList<>();
                String S = TF0.getText();
                if (S.length() > 0)
                    {
                        for(Etablissement E : ALE)
                        {
                            if(E.getNom().contains(S))
                            {
                                ALE1.add(E);
                                System.out.println(E.getNom());
                            } else {
                            }
                        }
                    }
                C.removeAll();
                FillTheContainers(ALE1);
                F.refreshTheme();
            }
        });*/
        F.add(TF0).add(C);
        FillTheContainers(ALE);
    }
    
    private void FillTheContainers(ArrayList<Etablissement> ALE)
    {
        this.ALE = ALE;
        for(Etablissement E : ALE)
        {
            try 
            {
                Container C0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label L0 = new Label(E.getNom());
                String S0 = "file://C:/wamp/www/Bons_Plans/web/img/" + E.getImage();
                Image img;
                img = Image.createImage(FileSystemStorage.getInstance().openInputStream(S0));
                ImageViewer IV0 = new ImageViewer();
                IV0.setImage(img.scaled(100, 100));
                C0.add(IV0).add(L0);
                C.add(C0);
            } 
            catch (IOException ex) 
            {
                System.out.println(ex);
            }
        }
        
    }

    /**
     * @return the ALE
     */
    public ArrayList<Etablissement> getALE() 
    {
        return ALE;
    }

    /**
     * @param ALE the ALE to set
     */
    public void setALE(ArrayList<Etablissement> ALE) 
    {
        this.ALE = ALE;
    }

    /**
     * @return the F
     */
    public Form getF() 
    {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(Form F) 
    {
        this.F = F;
    }
    
}
