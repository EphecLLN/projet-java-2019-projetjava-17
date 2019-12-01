/**
 * 
 */
package projetQuizz.vue;

import projetQuizz.Quizz;
import projetQuizz.modele.Theme;

/**
 * @author Flo
 *
 */
public abstract class InterfaceDeJeu {
	
	private Quizz quizz;
	
	public InterfaceDeJeu(Quizz quizz) {
		this.quizz = quizz;
	}
	
	public abstract void choisirTheme(Theme[] themesPossibles) throws Exception;
	public abstract void demanderNom() throws Exception;

	public Quizz getQuizz() {
		return quizz;
	}

	public abstract void afficherErreur(Exception e);
		
	

}
