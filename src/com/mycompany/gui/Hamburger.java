package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Hamburger 
{
    public void CreateHams(Form F)
    {
        F.getToolbar().addCommandToSideMenu("Accueil", null, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                HomeForm HF = new HomeForm();
                HF.getF().show();
            }
        });
        F.getToolbar().addCommandToSideMenu("Ajouter Une Demande", null, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                UneDemande UD = new UneDemande();
                UD.getF().show();
            }
        });
    }
}
