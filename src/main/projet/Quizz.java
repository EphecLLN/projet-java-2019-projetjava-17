/**
 * 
 */
package projet;

/**
 * @author obi-o
 *
 */
public class Quizz {

	private static int compteurQuestion = 0;
	private static int nombreQuestionRatees = 0;
	
	private static String theme = "";
	private static String difficulte = "";
	
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
		Question maQuestion = new Question();
		for (setCompteurQuestion(0);getCompteurQuestion()<10;setCompteurQuestion((getCompteurQuestion()+1))) {
			maQuestion.genererQuestion();
		}
	}
	public static void main (String[] args){
		Quizz quizz = new Quizz();
		quizz.questionner();
		}
}
