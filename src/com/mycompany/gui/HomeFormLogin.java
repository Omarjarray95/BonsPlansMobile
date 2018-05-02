/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import entities.Session;
import com.mycompany.Services.ServiceUser;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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

/**
 *
 * @author DADOU
 */
public class HomeFormLogin {
    Form f3;
    Form f;
    TextField temail;
    TextField tpassword;
    Button login;
    Button inscrire;
String Email;
String Password;
        Button Rec;
    public Form getF() {
        return f;
    }
public Form getF3() {
        return f;
    }
    public void setF(Form f) {
        this.f = f;
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

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }
    
    
     public HomeFormLogin (){
        f = new Form("Login",new FlowLayout(Component.CENTER,Component.CENTER));
       
        temail = new TextField("","Your Email");
        tpassword = new TextField("","Your Password");
        login = new Button("Log In");
        inscrire= new Button("S'iscrire");
        Container c = new Container(BoxLayout.y());
        c.add(temail);
        c.add(tpassword);
        c.add(login);
        c.add(inscrire);       
        f.add(c);
       
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //if(temail.getText().trim().isEmpty() && tpassword.getText().equals("123"))
                //{
                    Session S = new Session();
                    S.recupemail = temail.getText();
                    S.recupmdp=tpassword.getText();
                    Label lb= new Label();
                    lb.setText(S.getList2(temail.getText(), tpassword.getText()).toString());
                    System.out.println(S.recupemail);
                    
                    Form f2 =new Form("Welcome ", new FlowLayout(Component.CENTER,Component.CENTER));
                    Label la = new Label("Application SideBar");
                    f2.getToolbar().addComponentToSideMenu(la);
                    f2.getToolbar().addMaterialCommandToSideMenu("HOME",FontImage.MATERIAL_HOME, null );
                    f2.getToolbar().addMaterialCommandToSideMenu("About",FontImage.MATERIAL_BOOK, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                             f3 = new Form("Compte",BoxLayout.y());
                             Label lb = new Label();
                             Session ss=new Session();
                             lb.setText(ss.getList2(temail.getText(),tpassword.getText()).toString());
                             System.out.println("lala"+ss.user);
                             SpanLabel s = new SpanLabel( ss.user.toString());
                             //f3.add(lb);
                             
                             
                            f3.add(s);
                             //f3.getToolbar().addMaterialCommandToOverflowMenu("Home",FontImage.MATERIAL_ARROW_BACK, (e)->f.showBack());
                              f3.getToolbar().addMaterialCommandToOverflowMenu("Ajouter Reclamation",FontImage.MATERIAL_CONTACTS, new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent evt) {
                                   Session ses = new Session();
                                   Label lb = new Label();
                                   lb.setText(ses.getList2(temail.getText(), tpassword.getText()).toString());
                                  System.out.println(ses.user.getId());
                                   HomeFormRec Rec = new HomeFormRec();
                                  
                                  Rec.id_user=ses.user.getId();
                                  
                                  Rec.getF().show();

                                  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }
                             });
                              f3.getToolbar().addMaterialCommandToOverflowMenu("Mes Reclamations",FontImage.MATERIAL_MESSAGE, new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent evt) {
                                   Session ses = new Session();
                                   Label lb = new Label();
                                   lb.setText(ses.getList2(temail.getText(), tpassword.getText()).toString());
                                  System.out.println("OK!"+ses.user.getId());
                                   AffichageRec R = new AffichageRec();
                                  
                                  R.id_user=ses.user.getId();
                                  
                                  R.getF().show();

                                  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }
                             });
                             f3.getToolbar().addMaterialCommandToOverflowMenu("Log Out",FontImage.MATERIAL_ARROW_BACK, e->f.showBack());
                             f3.getToolbar().addMaterialCommandToOverflowMenu("Désactiver le compte", FontImage.MATERIAL_CLEAR, new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent evt) { 
                                    // (e)->{
                                         ServiceUser ser= new ServiceUser();
                                  Session ss = new Session();
                                  ser.DeleteUser(ss.user);
                                  Form ff = new Form();
                                  Label ll = new Label("Votre compte a ete désactivé !"+"\n"+ "Creer un autre compte :) " );
                                  Button btn = new Button("S'inscrire");
                                 ff.add(ll);
                                             ff.add(btn);
                                  btn.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent evt) {
                                                HomeFormUser hh=new HomeFormUser();
                                                hh.getF().show();
                                                
                                                 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                             }
                                             
                                             
                                         });
                                 
                                             ff.show();
                                          
                              
                                 
                                  
                                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                 }
                             });
                                     
                             f3.show();
                        }
                    } );
                    f2.show(); 
                    
        }
            
   
                  //  else       
                //     Dialog.show("ERROR !", " lOGIN OU MOT DE PASSE INCORRECT !", "Okay",null);
           // }
        });
         inscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeFormUser h = new HomeFormUser();
                h.getF().show();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
     }
}
