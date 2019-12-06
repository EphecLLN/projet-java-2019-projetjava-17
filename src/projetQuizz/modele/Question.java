/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Question {

	private int id;
	private String question;
	private Reponse[] reponses;

	Question(int id, String question, Reponse[] reponses) {
		this.id = id;
		this.question = question;
		this.reponses = reponses;
	}
}
