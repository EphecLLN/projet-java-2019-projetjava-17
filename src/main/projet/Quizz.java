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
	
	private int numTheme;
	private String difficulte = "";
	
	public Quizz(int numTheme, String difficulte) {
		this.numTheme = numTheme;
		this.difficulte = difficulte;
	}
	
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
	public int getTheme() {
		return numTheme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(int numTheme) {
		this.numTheme = numTheme;
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

	public static void main (String[] args){
		//Utilisateur user = new Utilisateur();
		Repertoire monRep = new Repertoire();
		String[] mesThemes = monRep.getThemes();
		
		System.out.println("Voici les thèmes disponibles :");
		for (int i = 0;i<mesThemes.length;i++) {
			System.out.println((i+1) + ". " + mesThemes[i]);
		}
		
		int boucle = 0;
		String choixTheme = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel sera votre thème ?");
		
		while (boucle != 1) {
			choixTheme = sc.nextLine();
			if (Repertoire.isNumeric(choixTheme) == true && Integer.parseInt(choixTheme)<=mesThemes.length && 
					Integer.parseInt(choixTheme) > 0) {
				boucle = 1;
			}
			else {
				System.out.println("Mauvais choix encodé. Utilisez bien les numéros disponible !");
			}
		}
		
		int choixFinal = Integer.parseInt(choixTheme)-1;
		int points;
		int pointsTotaux = 0;
		Question maQuestion;
		
		
		Quizz quizz = new Quizz(choixFinal, "facile");
		for (int j = 1;j <4;j++) {
			maQuestion = new Question(choixFinal,Integer.toString(j));
			points = maQuestion.genererQuestion();
			if (points == 0) {
				quizz.nombreQuestionRatees++;
			}
			quizz.compteurQuestion++;
			pointsTotaux += points;
			System.out.println("Vous avez actuellement " + pointsTotaux + " points");
		}
		System.out.println("Félicitations ! Vous avez fini le quizz sur le thème \"" + mesThemes[choixFinal] 
				+ "\" avec un score final de " + pointsTotaux + " points !" );
		System.out.println("Vous avez répondu correctement à " +  (quizz.compteurQuestion-quizz.nombreQuestionRatees) 
				+ " questions sur " + quizz.compteurQuestion);
		sc.close();
		
	}
}
