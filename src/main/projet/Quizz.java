/**
 * 
 */
package projet;
import java.util.*;

/**
 * @author Gauthier Verschraegen
 *
 */
public class Quizz {

	private int compteurQuestion = 0;
	private int nombreQuestionRatees = 0;
	
	private String theme = "";
	private String difficulte = "";
	
	/**
	 * @return the compteurQuestion
	 */
	public int getCompteurQuestion() {
		return compteurQuestion;
	}

	/**
	 * @param compteurQuestion the compteurQuestion to set
	 */
	public void setCompteurQuestion(int compteurQuestion) {
		this.compteurQuestion = compteurQuestion;
	}

	/**
	 * @return the nombreQuestionRatees
	 */
	public int getNombreQuestionRatees() {
		return nombreQuestionRatees;
	}

	/**
	 * @param nombreQuestionRatees the nombreQuestionRatees to set
	 */
	public void setNombreQuestionRatees(int nombreQuestionRatees) {
		this.nombreQuestionRatees = nombreQuestionRatees;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the difficulte
	 */
	public String getDifficulte() {
		return difficulte;
	}

	/**
	 * @param difficulte the difficulte to set
	 */
	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

	public void questionner() {
		
		Question maQuestion;
		String i;
		//Scanner sc = new Scanner(System.in);
		//for (setCompteurQuestion(0);getCompteurQuestion()<10;setCompteurQuestion((getCompteurQuestion()+1))) {
			maQuestion = new Question();
			maQuestion.genererQuestion();
			//i = sc.nextLine();
		//}
	}
	public static void main (String[] args){
		//Utilisateur user = new Utilisateur();
		Quizz quizz = new Quizz();
		quizz.questionner();
		}
}
