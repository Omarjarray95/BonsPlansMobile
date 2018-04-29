package entities;

public class Demande 
{
    private int id;
    private int iduser;
    private String nom;
    private String type;
    private String adresse;
    private String description;
    private String horaire_ouverture;
    private String horaire_fermeture;
    private int numtel;
    private String url;
    private int budgetmoyen;
    private String image;
    private String type_resto;
    private String type_loisirs;
    private String type_shops;
    private String nbrStars;
    
    public Demande(int iduser, String nom, String type, String adresse, String description, String horaire_ouverture, 
    String horaire_fermeture, int numtel, String url, int budgetmoyen, String image, String type_resto, String type_loisirs,
    String type_shops, String nbrStars)
    {
        this.iduser = iduser;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.horaire_ouverture = horaire_ouverture;
        this.horaire_fermeture = horaire_fermeture;
        this.numtel = numtel;
        this.url = url;
        this.budgetmoyen = budgetmoyen;
        this.image = image;
        this.type_resto = type_resto;
        this.type_shops = type_shops;
        this.type_loisirs = type_loisirs;
        this.nbrStars = nbrStars;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the iduser
     */
    public int getIduser() {
        return iduser;
    }

    /**
     * @param iduser the iduser to set
     */
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the horaire_ouverture
     */
    public String getHoraire_ouverture() {
        return horaire_ouverture;
    }

    /**
     * @param horaire_ouverture the horaire_ouverture to set
     */
    public void setHoraire_ouverture(String horaire_ouverture) {
        this.horaire_ouverture = horaire_ouverture;
    }

    /**
     * @return the horaire_fermeture
     */
    public String getHoraire_fermeture() {
        return horaire_fermeture;
    }

    /**
     * @param horaire_fermeture the horaire_fermeture to set
     */
    public void setHoraire_fermeture(String horaire_fermeture) {
        this.horaire_fermeture = horaire_fermeture;
    }

    /**
     * @return the numtel
     */
    public int getNumtel() {
        return numtel;
    }

    /**
     * @param numtel the numtel to set
     */
    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the budgetmoyen
     */
    public int getBudgetmoyen() {
        return budgetmoyen;
    }

    /**
     * @param budgetmoyen the budgetmoyen to set
     */
    public void setBudgetmoyen(int budgetmoyen) {
        this.budgetmoyen = budgetmoyen;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the type_resto
     */
    public String getType_resto() {
        return type_resto;
    }

    /**
     * @param type_resto the type_resto to set
     */
    public void setType_resto(String type_resto) {
        this.type_resto = type_resto;
    }

    /**
     * @return the type_loisirs
     */
    public String getType_loisirs() {
        return type_loisirs;
    }

    /**
     * @param type_loisirs the type_loisirs to set
     */
    public void setType_loisirs(String type_loisirs) {
        this.type_loisirs = type_loisirs;
    }

    /**
     * @return the type_shops
     */
    public String getType_shops() {
        return type_shops;
    }

    /**
     * @param type_shops the type_shops to set
     */
    public void setType_shops(String type_shops) {
        this.type_shops = type_shops;
    }

    /**
     * @return the nbrStars
     */
    public String getNbrStars() {
        return nbrStars;
    }

    /**
     * @param nbrStars the nbrStars to set
     */
    public void setNbrStars(String nbrStars) {
        this.nbrStars = nbrStars;
    }
    
    
}
