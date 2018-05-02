/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DADOU
 */
public class Reclamation {
   private int id_rec;
   private int id_user;
   private int id_etab;
   private String sujet;
   private String contenu_rec;
    
   public Reclamation(){
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }
   
   public Reclamation(int id_rec, String sujet, String contenu_rec) {
        this.id_rec = id_rec;
        this.sujet = sujet;
        this.contenu_rec = contenu_rec;
    }

    public Reclamation(int id_rec, int id_user, String sujet, String contenu_rec) {
        this.id_rec = id_rec;
        this.id_user = id_user;
        this.sujet = sujet;
        this.contenu_rec = contenu_rec;
    }

    public Reclamation(int id_rec, int id_user, int id_etab, String sujet, String contenu_rec) {
        this.id_rec = id_rec;
        this.id_user = id_user;
        this.id_etab = id_etab;
        this.sujet = sujet;
        this.contenu_rec = contenu_rec;
    }
    
    

   

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu_rec() {
        return contenu_rec;
    }

    public void setContenu_rec(String contenu_rec) {
        this.contenu_rec = contenu_rec;
    }

    @Override
    public String toString() {
        return "*" + "sujet:" + sujet + ", contenu_rec:" + contenu_rec + "\n";
    }
   
    
}
