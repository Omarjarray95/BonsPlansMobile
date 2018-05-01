package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import static com.codename1.ui.Display.GALLERY_IMAGE;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Services.DemandeService;
import entities.Demande;
import java.io.IOException;

public class UneDemande 
{
    Form F;
    TextField TF6 = new TextField();
    private TextField TF2 = new TextField();
    
    public UneDemande()
    {
        F = new Form("Demande D'Ajout", new BoxLayout(BoxLayout.Y_AXIS));
        Hamburger H = new Hamburger();
        H.CreateHams(F);
        Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L0 = new Label("Nom : ");
        TextField TF0 = new TextField();
        C0.add(L0).add(TF0);
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L1 = new Label("Type : ");
        ComboBox CB0 = new ComboBox("Restaurants/Cafés", "Boutiques", "Hotels", "Autres Etablissements"); 
        CB0.setSelectedIndex(0);
        ComboBox CB1 = new ComboBox("Restaurant", "Café", "Fast-Food", "Salon De Thé", "Boite De Nuit", "Glacier", "Autre");
        CB1.setSelectedIndex(0);
        TextField TF1 = new TextField();
        TF1.setHint("Saisissez ...");
        TF1.setHidden(true);
        CB0.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                ListenToTheCB(CB0, CB1);
            }
        });
        CB1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                if (CB1.getSelectedItem().toString().equals("Autre"))
                {
                TF1.setHidden(false);
                }
            }
        });
        C1.add(L1).add(CB0).add(CB1).add(TF1);
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C22 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label L2 = new Label("Adresse : ");
        Button B2 = new Button("Utiliser Google Maps");
        
        C22.add(L2).add(B2);
        C2.add(C22).add(TF2);
        B2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                GoogleMaps M = new GoogleMaps(new Coord(36.8386651, 10.0304474));
            }
        });
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L3 = new Label("Description : ");
        TextArea TA3 = new TextArea(15, 15);
        TA3.setMaxSize(2000);
        C3.add(L3).add(TA3);
        Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Picker P5 = new Picker();
        P5.setType(Display.PICKER_TYPE_TIME);
        Picker P4 = new Picker();
        P4.setType(Display.PICKER_TYPE_TIME);
        Label L5 = new Label (" - ");
        C5.add(P5).add(L5).add(P4);
        Label L4 = new Label("Horaire : ");
        C4.add(L4).add(C5);
        Container C6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L6 = new Label("Numéro De Contact : ");
        TF6.setConstraint(TextField.NUMERIC);
        TF6.addPointerPressedListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                CreateVirtualKeyboard(TF6).show(10, 80, 30, 30);
                TF6.setEnabled(false);
            }
        });
        C6.add(L6).add(TF6);
        Container C7 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L7 = new Label("Site Web/Blog : ");
        TextField TF7 = new TextField();
        TF7.setConstraint(TextField.URL);
        C7.add(L7).add(TF7);
        Container C8 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label L8 = new Label("Budget Moyen : ");
        TextField TF8 = new TextField();
        TF8.setConstraint(TextField.NUMERIC);
        TF8.addPointerPressedListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                CreateVirtualKeyboard(TF8).show(10, 80, 30, 30);
                TF8.setEnabled(false);
            }
        });
        C8.add(L8).add(TF8);
        Container C9 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //ImageViewer IV9 = new ImageViewer();
        //IV9.setHeight(400); IV9.setWidth(400);
        Button B9 = new Button("Choisissez Votre Image");
        Label L9 = new Label();
        B9.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                Display.getInstance().openGallery(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent evt) 
                    {
                        if (evt != null && evt.getSource() != null) 
                        {
                        String path = (String) evt.getSource();
                        System.out.println(path);
                        Image img = null;
                        try 
                        {
                        String[] SP = Util.split(path, "/");
                        String SP0 = SP[SP.length - 1];
                        Util.copy(FileSystemStorage.getInstance().openInputStream(path), FileSystemStorage.getInstance().openOutputStream("file://C:/wamp/www/Bons_Plans/web/img/" + SP0));
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                        //IV9.setImage(img);
                        L9.setText(SP0);
                        } 
                        catch (IOException e) 
                        {
                        System.out.println(e.getMessage());
                        }
                        } 
                    }
                }, GALLERY_IMAGE);
            }
        });
        //C9.add(IV9); 
        C9.add(B9); 
        Container C10 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Button B10 = new Button("Soumettre");
        B10.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                boolean B = true;
                String N0 = null;
                if (!"".equals(TF0.getText()))
                {
                N0 = TF0.getText();
                }
                else 
                {
                    Label L10 = new Label("Vous Devez Nous Fournir Le Nom De L'Etablissement");
                    C10.add(L10);
                    B = false;
                }
                String N1 = null;
                if (CB0.getSelectedItem() != null)
                {
                N1 = CB0.getSelectedItem().toString();
                }
                else
                {
                    Label L11 = new Label("Vous Devez Nous Fournir Le Type De L'Etablissement");
                    C10.add(L11);
                    B = false;
                }
                String N2 = null;
                if (CB1.getSelectedItem() != null)
                {
                N2 = CB1.getSelectedItem().toString();
                }
                else
                {
                    Label L12 = new Label("Vous Devez Nous Fournir Le Type De L'Etablissement");
                    C10.add(L12);
                    B = false;
                }
                String N3 = null;
                if (!TF2.getText().equals(""))
                {
                N3 = getTF2().getText();
                }
                else
                {
                    Label L13 = new Label("Vous Devez Nous Fournir L'Adresse De L'Etablissement");
                    C10.add(L13);
                    B = false;
                }
                String N4 = null;
                if (!TA3.getText().equals(""))
                {
                N4 = TA3.getText();
                }
                String N5;
                String N6;
                int I5 = P5.getTime()/60; int IM5 = P5.getTime()%60;
                String N55;
                String N555;
                if (I5 < 10)
                {
                    N55 = "0" + Integer.toString(I5);
                }
                else
                {
                    N55 = Integer.toString(I5);
                }
                if (IM5 < 10)
                {
                    N555 = "0" + Integer.toString(IM5);
                }
                else
                {
                    N555 = Integer.toString(IM5);
                }
                N5 = N55 + ":" + N555;
                int I6 = P4.getTime()/60; int IM6 = P5.getTime()%60;
                String N66;
                String N666;
                if (I6 < 10)
                {
                    N66 = "0" + Integer.toString(I6);
                }
                else
                {
                    N66 = Integer.toString(I6);
                }
                if (IM6 < 10)
                {
                    N666 = "0" + Integer.toString(IM6);
                }
                else
                {
                    N666 = Integer.toString(IM6);
                }
                N6 = N66 + ":" + N666;
                String N7 = null;
                if ((!TF6.getText().equals("")) && (TF6.getText().length() == 8))
                {
                    N7 = TF6.getText();
                }
                else
                {
                    Label L14 = new Label("Vous Devez Nous Fournir Un Numéro Valide");
                    C10.add(L14);
                    B = false;
                }
                String N8 = null;
                if (!TF7.getText().equals(""))
                {
                N8 = TF7.getText();
                }
                String N9 = null;
                if (!TF8.getText().equals(""))
                {
                N9 = TF8.getText();
                }
                String N10 = null;
                if (!L9.getText().equals(""))
                {
                    N10 = L9.getText();
                }
                else
                {
                    Label L15 = new Label("Vous Devez Nous Fournir Une Image Principale Pour L'Etablissement");
                    C10.add(L15);
                    B = false;
                }
                /*if (B == true)
                {*/
                Demande D = new Demande(1,N0,N1,N3,N4,N5,N6,Integer.parseInt(N7),N8,Integer.parseInt(N9),N10,null,null,null,null);
                if ((N1 != null) && (N1.equals("Restaurants/Cafés")))
                {
                    D.setType_resto(N2);
                }
                if ((N1 != null) && (N1.equals("Boutiques")))
                {
                    D.setType_shops(N2);
                }
                if ((N1 != null) && (N1.equals("Autres Etablissements")))
                {
                    D.setType_loisirs(N2);
                }
                if ((N1 != null) && (N1.equals("Hotels")))
                {
                    D.setNbrStars(N2);
                }
                DemandeService DS = new DemandeService();
                DS.AjouterDemande(D);
                //}
            }
        });
        C10.add(B10);
        F.add(C0).add(C1).add(C2).add(C3).add(C4).add(C6).add(C7).add(C8).add(C9).add(C10);
    }
    
    public void ListenToTheCB(ComboBox CB0, ComboBox CB1)
    {
        if (CB0.getSelectedItem().toString().equals("Restaurants/Cafés"))
                {
                    DefaultListModel DLM = (DefaultListModel) CB1.getModel();
                    DLM.removeAll();
                    CB1.addItem("Restaurant");
                    CB1.addItem("Café");
                    CB1.addItem("Fast-Food");
                    CB1.addItem("Salon De Thé");
                    CB1.addItem("Boite De Nuit");
                    CB1.addItem("Bar");
                    CB1.addItem("Glacier");
                    CB1.addItem("Autre");
                }
                if (CB0.getSelectedItem().toString().equals("Boutiques"))
                {
                    DefaultListModel DLM = (DefaultListModel) CB1.getModel();
                    DLM.removeAll();
                    CB1.addItem("Grande Surface");
                    CB1.addItem("Boutique");
                    CB1.addItem("Fleuriste");
                    CB1.addItem("Librairie");
                    CB1.addItem("Candy Shop");
                    CB1.addItem("Parfumerie");
                    CB1.addItem("Patisserie");
                    CB1.addItem("Autre");
                }
                if (CB0.getSelectedItem().toString().equals("Autres Etablissements"))
                {
                    DefaultListModel DLM = (DefaultListModel) CB1.getModel();
                    DLM.removeAll();
                    CB1.addItem("Cinéma");
                    CB1.addItem("Salle De Sport");
                    CB1.addItem("Parc D'Attraction");
                    CB1.addItem("Spa");
                    CB1.addItem("Salon De Coiffure");
                    CB1.addItem("Salle De Jeux");
                    CB1.addItem("Autre");
                }
                if (CB0.getSelectedItem().toString().equals("Hotels"))
                {
                    DefaultListModel DLM = (DefaultListModel) CB1.getModel();
                    DLM.removeAll();
                    CB1.addItem("*");
                    CB1.addItem("* *");
                    CB1.addItem("* * *");
                    CB1.addItem("* * * *");
                    CB1.addItem("* * * * *");
                }
    }
    
    public InteractionDialog CreateVirtualKeyboard(TextField TF)
    {
        InteractionDialog D = new InteractionDialog();
        Container D00 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container D01 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Container C00 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Container C01 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Container C02 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Container C03 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        Container C04 = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
        TextField TF06 = new TextField();
        TF06.setText(TF.getText());
        Button B01 = new Button("1");
        Button B02 = new Button("2");
        Button B03 = new Button("3");
        Button B04 = new Button("4");
        Button B05 = new Button("5");
        Button B06 = new Button("6");
        Button B07 = new Button("7");
        Button B08 = new Button("8");
        Button B09 = new Button("9");
        Button B00 = new Button("0");
        Button B11 = new Button("DEL");
        Button B12 = new Button("ESC");
        Button B13 = new Button("RES");
        Button B14 = new Button("OK");
        ListenToTheB(B01, TF06); ListenToTheB(B02, TF06); ListenToTheB(B03, TF06); ListenToTheB(B04, TF06); 
        ListenToTheB(B05, TF06); ListenToTheB(B06, TF06); ListenToTheB(B07, TF06); ListenToTheB(B08, TF06); 
        ListenToTheB(B09, TF06); ListenToTheB(B00, TF06); 
        B11.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                if (TF06.getText().length() > 0)
                {
                TF06.setText(TF06.getText().substring(0, TF06.getText().length() - 1));
                }
            }
        });
        B13.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                TF06.setText("");
            }
        });
        B12.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                TF.setText("");
                D.dispose();
                TF.setEnabled(true);
            }
        });
        B14.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                TF.setText(TF06.getText());
                D.dispose();
                TF.setEnabled(true);
            }
        });
        C00.add(B01).add(B02).add(B03);
        C01.add(B04).add(B05).add(B06);
        C02.add(B07).add(B08).add(B09);
        C03.add(B13).add(B00).add(B11);
        C04.add(B14).add(B12);
        D00.add(TF06).add(C00).add(C01).add(C02).add(C03).add(C04);
        D01.add(D00);
        D.add(D01);
        return D;
    }
    
    public void ListenToTheB(Button B, TextField TF)
    {
        B.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                TF.setText(TF.getText() + B.getText());
            }
        });
    }
    
    public Form getF() 
    {
        return F;
    }

    public void setF(Form F) 
    {
        this.F = F;
    }

    /**
     * @return the TF2
     */
    public TextField getTF2() 
    {
        return TF2;
    }

    /**
     * @param TF2 the TF2 to set
     */
    public void setTF2(TextField TF2) 
    {
        this.TF2 = TF2;
    }
}
