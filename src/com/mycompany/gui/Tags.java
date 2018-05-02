package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.Services.EtablissementService;
import com.mycompany.Services.TagService;
import entities.Etablissement;
import entities.Tag;
import java.util.ArrayList;

public class Tags 
{
    private Form F;
    ArrayList<Tag> ALT = new ArrayList<>();
    ArrayList<Tag> ALTNE = new ArrayList<>();
    
    public Tags(Etablissement E)
    {
        F = new Form("Gérer Les Tags", new BoxLayout(BoxLayout.Y_AXIS));
        Container C0 = new Container(new FlowLayout(Component.CENTER, Component.TOP));
        Container C00 = new Container(new FlowLayout(Component.CENTER, Component.TOP));
        Label L0 = new Label("Tags De " + E.getNom());
        C00.add(L0).add(C0);
        F.add(C00);
        ALT = E.getTag();
        for(Tag T : ALT)
        {
            Button B0 = new Button(T.getNom());
            B0.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    EtablissementService ES = new EtablissementService();
                    ArrayList<Etablissement> ALE = ES.getEtablissementsParTags(B0.getText());
                    Recherches R = new Recherches(ALE);
                    R.getF().show();
                }
            });
            C0.add(B0);
        }
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField TF2 = new TextField();
        TF2.setHint("Ajoutez Votre Tag...");
        Button B2 = new Button("Ajouter");
        C2.add(TF2).add(B2);
        B2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                if (!TF2.getText().equals(""))
                {
                boolean B = true;
                for(Tag T : ALT)
                {
                    if (T.getNom().equals(TF2.getText()))
                    {
                        B = false;
                    }
                }
                if (B == true)
                {
                Tag T = new Tag(1,TF2.getText());
                TagService TS = new TagService();
                TS.AjouterTag(E, T);
                Dialog D = new Dialog(new FlowLayout(Component.CENTER, Component.CENTER));
                Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label L3 = new Label("Tag Ajouté");
                Button B3 = new Button("OK");
                B3.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent evt) 
                    {
                        D.dispose();
                        TF2.setText("");
                    }
                });
                C3.add(L3).add(B3);
                D.add(C3);
                D.show();
                }
                }
                else
                {
                    Dialog D1 = new Dialog(new FlowLayout(Component.CENTER, Component.CENTER));
                    Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label L3 = new Label("Tag Existe Déjà");
                    Button B3 = new Button("OK");
                    B3.addActionListener(new ActionListener() 
                    {
                    @Override
                    public void actionPerformed(ActionEvent evt) 
                    {
                        D1.dispose();
                    }
                    });
                    C3.add(L3).add(B3);
                    D1.add(C3);
                    D1.show();
                }
            }
        });
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "TitleCommand", 3);
        F.getToolbar().addCommandToLeftBar("", icon, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                UnEtablissement UE = new UnEtablissement(E);
                UE.getF().show();
            }
        });
        Container C3 = new Container(new FlowLayout(Component.CENTER, Component.TOP));
        Container C30 = new Container(new FlowLayout(Component.CENTER, Component.TOP));
        Label L1 = new Label("Tags Disponibles");
        C30.add(L1).add(C3);
        TagService TS = new TagService();
        ALTNE = TS.getTagsNonExistants(Integer.toString(E.getId()));
        for(Tag T : ALTNE)
        {
            Button B = new Button(T.getNom());
            B.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    TF2.setText(B.getText());
                }
            });
            C3.add(B);
        }
        F.add(C2).add(C30);
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
