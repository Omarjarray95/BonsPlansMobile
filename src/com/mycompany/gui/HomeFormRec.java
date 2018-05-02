/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import entities.Reclamation;
import com.mycompany.Services.ServiceReclamation;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.mycompany.Services.EtablissementService;
import entities.Etablissement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DADOU
 */
public class HomeFormRec {
      Form f;
    ComboBox<String> Etab;
    TextField tsujet;
    TextArea tcontenuRec;
    Button btnajout,btnaff;
   public int id_user;
    public TextField getTsujet() {
        return tsujet;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

    public void setTsujet(TextField tsujet) {
        this.tsujet = tsujet;
    }

    public TextArea getTcontenuRec() {
        return tcontenuRec;
    }

    public void setTcontenuRec(TextArea tcontenuRec) {
        this.tcontenuRec = tcontenuRec;
    }

    public HomeFormRec() {
        f = new Form("home");
        tsujet = new TextField("");
        
        tcontenuRec = new TextArea(10, 15);
        tcontenuRec.setMaxSize(100);

        btnajout = new Button("Envoyer");
        
      //btnaff=new Button("Les reclamations");
        ArrayList<Etablissement> ListeEtablissements = new ArrayList<>();
        EtablissementService EtabS = new EtablissementService();
        ListeEtablissements=EtabS.getListEtab();
        

            for (Etablissement obj : ListeEtablissements) 
            {
                
             Etab = new ComboBox<String>(obj.getNom());
            
            }
        
        
         
        f.add(tsujet);
        
        f.add(tcontenuRec);
        f.add(Etab);
        
        f.add(btnajout);
        //f.add(btnaff);
        
        
        btnajout.addActionListener((e) -> {
            String n = Etab.getSelectedItem();
            ServiceReclamation ser = new ServiceReclamation();
            int id_etab ;
            
            Etablissement E= new Etablissement();
            EtablissementService etabS = new EtablissementService();
            
            E=etabS.getEtablissementsParNom(n);
            id_etab=E.getId();
            Reclamation R = new Reclamation(0,id_user, id_etab, tsujet.getText(), tcontenuRec.getText());
            System.out.println(id_etab);
            System.out.println(R);  
            System.out.println("haw hnééé"+ser.getRecParIdEtab(id_etab));
             ser.ajoutReclamation(R);
            HomeFormLogin l = new HomeFormLogin();
            l.getF3().show();
            
        });
        
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeFormRec h=new HomeFormRec();});
        /*btnaff.addActionListener((e)->{
        AffichageRec a=new AffichageRec();
        a.getF().show();
        });*/
    }

    public Form getF() {
        return f;
    
}

}