package projetQuizz.modele;

import java.util.ArrayList;

public class Resultat {

    private Question question;
    private int score;
    private boolean estBonneReponse;

    /**
     * Constructeur de la classe Resultat.
     */
    public Resultat(Question question, int score, boolean estBonneReponse) {
        this.question = question;
        this.score = score;
        this.estBonneReponse = estBonneReponse;
    }
    
    /**
     * Getter de bonneReponse.
     */
    public Reponse getBonneReponse() {
        ArrayList<Reponse> reponses = question.getReponses();
        for (int i=0; i<reponses.size(); i++) {
            if (reponses.get(i).getEstBonneReponse()) {
                return reponses.get(i);
            }
        }
        return null;
    }

    /**
     * Getter de score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter de estBonneReponse.
     */
    public boolean getEstBonneReponse() {
        return estBonneReponse;
    }
}
