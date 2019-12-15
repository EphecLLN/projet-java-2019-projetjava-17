package projetQuizz.modele;


public class Reponse {
    private boolean estBonneReponse;
    private String reponse;
    private int id;

    /**
     * Constructeur de la classe Reponse. 
     */
    Reponse(int id, String reponse) {
        this(id, reponse, false);
    }

    /**
     * Autre constructeur de la classe Reponse. 
     */
    Reponse(int id, String reponse, boolean estBonneReponse) {
        this.id = id;
        this.reponse = reponse;
        this.estBonneReponse = estBonneReponse;
    }

    /**
     * Getter de estBonneReponse.
     */
    public boolean getEstBonneReponse() {
        return estBonneReponse;
    }

    /**
     * Getter de reponse.
     */
    public String getReponse() {
        return reponse;
    }

}
