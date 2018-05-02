/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.Services.ServiceReclamation;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;

/**
 *
 * @author DADOU
 */
public class AffichageRec {
    
    Form f;
    SpanLabel lb;
   public int id_user;
       
    public AffichageRec() {
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceReclamation serviceRec=new ServiceReclamation();
        lb.setText(serviceRec.getList2(id_user).toString());
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeFormRec h=new HomeFormRec();
          h.getF().show();
          });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}
