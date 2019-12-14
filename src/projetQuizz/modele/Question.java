/**
 *
 */
package projetQuizz.modele;

import java.util.ArrayList;

/**
 * @author Flo
 *
 */
public class Question {

    private int id;
    private String question;
    private ArrayList<Reponse> reponses;

    Question(int id, String question, ArrayList<Reponse> reponses) {
        this.id = id;
        this.question = question;
        this.reponses = reponses;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<Reponse> getReponses() {
        return reponses;
    }

}
