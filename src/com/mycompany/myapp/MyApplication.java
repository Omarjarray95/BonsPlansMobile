package com.mycompany.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.mycompany.gui.HomeForm;

public class MyApplication 
{

    private Resources theme;

    public void init(Object context) 
    {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() 
    {
        HomeForm h = new HomeForm();
        h.getF().show();
    }

    public void stop() 
    {
        
    }
    
    public void destroy() 
    {
        
    }

}
