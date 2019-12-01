/**
 * 
 */
package projetQuizz.vue;

import java.util.Scanner;

import projetQuizz.Quizz;
import projetQuizz.modele.Theme;

/**
 * @author Flo
 *
 */
public class Console extends InterfaceDeJeu {

	private Scanner in;
	
	public Console(Quizz quizz) {
		super(quizz);
		in = new Scanner(System.in);
		}

	@Override
	public void choisirTheme(Theme[] themesPossibles) throws Exception {
		System.out.println("Choisissez parmis ces thèmes:");
		for (int i = 0; i < themesPossibles.length; i++) {
			System.out.println(Integer.toString(i + 1) + ". " + themesPossibles[i].getNom());
		}
		int index = in.nextInt();
		//TODO vérifier que la valeur reçu soit dans le tableau
		getQuizz().themeChoisi(themesPossibles[index - 1]);
		
	}

	@Override
	public void demanderNom() throws Exception {
		System.out.println("Quel est votre nom:");
		getQuizz().nomChoisi(in.nextLine());
		
	}

	@Override
	public void afficherErreur(Exception e) {
		System.out.println(e.getMessage());
		
	}

}
