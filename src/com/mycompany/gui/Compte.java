/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import entities.Session;
import com.mycompany.Services.ServiceReclamation;
import com.mycompany.Services.ServiceUser;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author DADOU
 */
public class Compte {
    Session ss=new Session();
    Form f;
    SpanLabel lb;
    Button AjoutRec;
    public Compte() {
       /* 
        f = new Form();
        //lb = new SpanLabel("");
        //f.add(lb);
       // Session ss=new Session();
          f = new Form("Compte",BoxLayout.y());
                             Label lb = new Label();
                             //Session ss=new Session();
                             //lb.setText(ss.getList2(temail.getText(),tpassword.getText()).toString());
                             //SpanLabel s = new SpanLabel( ss.user.toString());
                             //f3.add(lb);
                             //Button Rec= New Button("Ajouter Reclamation");
                             
                             //f.add(s);
                             //f3.getToolbar().addMaterialCommandToOverflowMenu("Home",FontImage.MATERIAL_ARROW_BACK, (e)->f.showBack());
                             f.getToolbar().addMaterialCommandToOverflowMenu("Log Out",FontImage.MATERIAL_ARROW_BACK, e->f.showBack());
                             f.getToolbar().addMaterialCommandToOverflowMenu("Désactiver le compte", FontImage.MATERIAL_CLEAR, new ActionListener() {
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
                                     
                             f.show();
                        }
                    //} );
                    //f2.show(); 
                    
        //}
            
   
                  //  else       
                //     Dialog.show("ERROR !", " lOGIN OU MOT DE PASSE INCORRECT !", "Okay",null);
           // }
       // });
         // }
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
 */   
}
}