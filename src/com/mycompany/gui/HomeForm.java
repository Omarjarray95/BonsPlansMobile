package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.StorageImage;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.Services.EtablissementService;
import entities.Etablissement;
import java.io.IOException;
import java.util.ArrayList;

public class HomeForm 
{
    Form F;
    ArrayList<Etablissement> ALE = new ArrayList<>();

    public HomeForm() 
    {
        Style S = UIManager.getInstance().getComponentStyle("TitleCommand");
        F = new Form("Accueil",new BoxLayout(BoxLayout.Y_AXIS));
        Hamburger H = new Hamburger();
        H.CreateHams(F);
        Container C0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button B0 = new Button("Restaurants/Cafés");
        Button B1 = new Button("Boutiques");
        Button B2 = new Button("Hotels");
        Button B3 = new Button("Autres Etablissements");
        B0.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ALE = DéclencherServiceType("Restaurants");
                FillContainers(ALE, C3);
                F.refreshTheme();
            }
        });
        B1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ALE = DéclencherServiceType("Boutiques");
                FillContainers(ALE, C3);
                F.refreshTheme();
            }
        });
        B2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ALE = DéclencherServiceType("Hotels");
                FillContainers(ALE, C3);
                F.refreshTheme();
            }
        });
        B3.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ALE = DéclencherServiceType("Autres");
                FillContainers(ALE, C3);
                F.refreshTheme();
            }
        });
        C0.add(B0); C0.add(B1); 
        C1.add(B2); C1.add(B3);
        F.add(C0); F.add(C1); F.add(C3);
    }
    
    public ArrayList<Etablissement> DéclencherServiceType(String type)
    {
        EtablissementService ES = new EtablissementService();
        return ES.getEtablissementsParType(type);
    }
    
    public void FillContainers(ArrayList<Etablissement> ALE, Container C)
    {
        C.removeAll();
        int i = 0;
        int j;
        if (ALE.size() > 4)
        {
            j = 5;
        }
        else
        {
            j = ALE.size();
        }
        for (Etablissement E:ALE)
                { 
                Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label L0 = new Label(E.getNom());
                L0.addPointerPressedListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent evt) 
                    {
                        UnEtablissement UE = new UnEtablissement(E);
                        UE.getF().show();
                    }
                });
                C2.add(L0);
                C.add(C2);
                i++;
                if (i == j)
                {
                    Button B4 = new Button("Afficher Tout");
                    C.add(B4);
                    break;
                }
                }
    }

    public Form getF() 
    {
        return F;
    }

    public void setF(Form F) 
    {
        this.F = F;
    }

}
