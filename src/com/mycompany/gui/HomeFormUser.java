/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import entities.User;
import com.mycompany.Services.ServiceUser;
import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DADOU
 */
public class HomeFormUser {
    
      Form f;
    TextField tnom;
    TextField tusername;
    TextField temail;
    TextField tpassword;
    TextField tdatedenaissance;
    CheckBox tsexe;
    TextField tville;
    TextField tphone;
    Button btnsinscrire;
    Button Login;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

    public TextField getTusername() {
        return tusername;
    }

    public void setTusername(TextField tusername) {
        this.tusername = tusername;
    }

    public TextField getTemail() {
        return temail;
    }

    public void setTemail(TextField temail) {
        this.temail = temail;
    }

    public TextField getTpassword() {
        return tpassword;
    }

    public void setTpassword(TextField tpassword) {
        this.tpassword = tpassword;
    }

    public TextField getTdatedenaissance() {
        return tdatedenaissance;
    }

    public void setTdatedenaissance(TextField tdatedenaissance) {
        this.tdatedenaissance = tdatedenaissance;
    }

    public CheckBox getTsexe() {
        return tsexe;
    }

    public void setTsexe(CheckBox tsexe) {
        this.tsexe = tsexe;
    }

    public TextField getTville() {
        return tville;
    }

    public void setTville(TextField tville) {
        this.tville = tville;
    }

    public TextField getTphone() {
        return tphone;
    }

    public void setTphone(TextField tphone) {
        this.tphone = tphone;
    }

    public Button getBtnsinscrire() {
        return btnsinscrire;
    }

    public void setBtnsinscrire(Button btnsinscrire) {
        this.btnsinscrire = btnsinscrire;
    }
public HomeFormUser() {
        f = new Form("home");
        
        
    
    tusername= new TextField();
    temail= new TextField();
    tpassword= new TextField();
    tdatedenaissance= new TextField();
    tsexe= new CheckBox("Femme");
    CheckBox chHomme = new CheckBox("Homme");
      
    tville= new TextField();
    tphone= new TextField();
    
  Container C =new Container(BoxLayout.x());
        Label l1 = new Label ("Nom:");
        tnom = new TextField();
        
        C.add(l1);
        C.add(tnom);
  
        
        Container C2 =new Container(BoxLayout.x());
        Label l2 = new Label ("Username:");
        tusername = new TextField();
        
        C2.add(l2);
        C2.add(tusername);
  
        Container C3 =new Container(BoxLayout.x());
        Label l3 = new Label ("Email:");
        temail = new TextField();
        
        C3.add(l3);
        C3.add(temail);
  
        Container C4 =new Container(BoxLayout.x());
        Label l4 = new Label ("Password:");
        tpassword = new TextField();
        
        C4.add(l4);
        C4.add(tpassword);
  
       /* Container C5 =new Container(BoxLayout.x());
        Label l5 = new Label ("Date de Naissance:");
        tdatedenaissance = new TextField();
        
        C5.add(l5);
        C5.add(tdatedenaissance);
  */
        Container C6 =new Container(BoxLayout.x());
        Label l6 = new Label ("Sexe:");
        tsexe = new CheckBox("Femme");
        chHomme = new CheckBox("Homme");
        
        
        C6.add(l6);
        C6.add(tsexe);
        C6.add(chHomme); 
        Container C7 =new Container(BoxLayout.x());
        Label l7 = new Label ("Ville:");
        tville = new TextField();
        
        C7.add(l7);
        C7.add(tville);
  
        Container C8 =new Container(BoxLayout.x());
        Label l8 = new Label ("Phone number:");
        tphone = new TextField();
        
        C8.add(l8);
        C8.add(tphone);
  
        btnsinscrire = new Button("S'inscrire");
       
      Login = new Button("J'ai déjà un compte");
       
      f.add(C);
        f.add(C2);
        f.add(C3);
        f.add(C4);
      //  f.add(C5);
        f.add(C6);
        f.add(C7);
        f.add(C8);
        f.add(btnsinscrire);
      f.add(Login);
        btnsinscrire.addActionListener((e) -> {
            ServiceUser ser = new ServiceUser();
            User U = new User( 0 ,tnom.getText(), tusername.getText(), temail.getText(), tpassword.getText(),
                    tsexe.getText(), tville.getText(), Integer.valueOf(tphone.getText()));
            
            ser.ajoutUser(U);
        });    
         btnsinscrire.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent evt){
          Form f2=new Form(BoxLayout.y());
          
          
          Label lNom= new Label("Vous avez inscrit Bienvenue Mr/Mme : ");
                  Label lb= new Label(tnom.getText());
                  Label lb2 = new Label("Veuillez vous connecter!");
          Button Login =new Button("Login");
          /*Label lPrenom= new Label("Prenom: "+tfPrenom.getText());
          
          Label lPwd= new Label("Password: "+tfPwd.getText());
          
          Label lGender= new Label("Gender: ");
          
          if (onOff.isValue()) lGender.setText("Gender : Homme");
          
          else lGender.setText("Gender: Femme");
          Label lHobis = new Label();
          String ch= "Hobis: ";
          
          if (chFoot.isSelected())ch+="Foot,";
          
          if (chTennis.isSelected())ch+="Tennis,";
          
          if (chLecture.isSelected())ch+="Lecture,";
          
          if (chNatation.isSelected())ch+="Natation,";
          
          
          lHobis.setText(ch);
          */
          f2.add(lNom);
          
          f2.add(lb);
          
          
          f2.add(lb2);
          f2.add(Login);
          /*f2.add(lPrenom);
          f2.add(lPwd);
          f2.add(lGender);
          f2.add(lHobis);
          */
          f2.show();
          
          
          
          
      
      }
      
      
      
      
        });
         
      Login.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent evt){
          HomeFormLogin h2=new HomeFormLogin();
          h2.getF().show();
      }}
          );
        
}
        
    
}
              
