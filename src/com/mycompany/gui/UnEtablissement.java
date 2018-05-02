package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.Services.EtablissementService;
import entities.Etablissement;

public class UnEtablissement 
{
    Etablissement E;
    Form F;
    
    public UnEtablissement(Etablissement E)
    {
        
        this.E = E;
        F = new Form(E.getNom(),new BoxLayout(BoxLayout.Y_AXIS));
        Hamburger H = new Hamburger();
        H.CreateHams(F);
        F.getToolbar().addCommandToOverflowMenu("Modifier", null, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ModificationE ME = new ModificationE(E);
                ME.getF().show();
            }
        });
        F.getToolbar().addCommandToOverflowMenu("Supprimer", null, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                EtablissementService ES = new EtablissementService();
                ES.SupprimerEtablissement(E);
                HomeForm HF = new HomeForm();
                HF.getF().show();
            }
        });
        Container C0 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Label L0 = new Label(E.getNom());
        C0.add(L0);
        Label L1 = new Label(E.getAdresse());
        SpanLabel L2 = new SpanLabel("Adresse : " + L1.getText());
        Label L3 = new Label(E.getDescription());
        SpanLabel L4 = new SpanLabel("Description : " + L3.getText());
        Label L5 = new Label(E.getType());
        Label L6 = new Label();
        if (E.getType().equals("Restaurants/Cafés"))
        {
            L6.setText(E.getType_resto());
        }
        if (E.getType().equals("Boutiques"))
        {
            L6.setText(E.getType_shops());
        }
        if (E.getType().equals("Autres Etablissements"))
        {
            L6.setText(E.getType_loisirs());
        }
        if (E.getType().equals("Hotels"))
        {
            L6.setText(E.getNbrStars());
        }
        Container C1 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        SpanLabel L7 = new SpanLabel("Type : " + L6.getText());
        C1.add(L7);
        Label L8 = new Label(E.getHoraire_ouverture());
        Label L9 = new Label(E.getHoraire_fermeture());
        Label L10 = new Label("Ouvert de " + L8.getText() + " à " + L9.getText());
        Label L11 = new Label(Integer.toString(E.getNumtel()));
        Label L12 = new Label("Contact : " + L11.getText());
        Label L13 = new Label(E.getUrl());
        SpanLabel L14 = new SpanLabel("Site Web/Blog : " + L13.getText());
        Label L15 = new Label(Integer.toString(E.getBudgetmoyen()));
        Label L16 = new Label("Budget Moyen : " + L15.getText() + " DT");
        Container C2 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Button B2 = new Button("Tags");
        B2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                Tags T = new Tags(E);
                T.getF().show();
            }
        });
        C2.add(B2);
        F.add(C0); F.add(C1).add(C2); F.add(L2).add(L10).add(L12).add(L14).add(L16); F.add(L4);
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
