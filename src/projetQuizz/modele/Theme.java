/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Theme {
	private String nom;
	private int id;
	private Question[] questions;

	Theme(int id, String nom, Question[] questions) {
		this.id = id;
		this.nom = nom;
		this.questions = questions;
	}

	public static Theme[] chargerThemes() {
		return new Theme[] {
				new Theme(1, "G�ograpie", new Question[] {
						new Question(1, "Quel tropique se trouve dans l'h�misph�re nord ?", new Reponse[] {
								new Reponse(1, "Oulan-Bator"), 
								new Reponse(2, "Capricorne"),
								new Reponse(3, "Equateur"),
								new Reponse(4, "Mer Noire"),
						}),
						new Question(2, "Quelle est la capitale de la Mongolie ?", new Reponse[] {
								new Reponse(1, "Cancer"), 
								new Reponse(2, "Kaboul"),
								new Reponse(3, "Bagdad"),
								new Reponse(4, "Douchanb�"),
						}),
				}),
				new Theme(2, "L'espace", new Question[] {
						new Question(1, "Quel est l'�ge approximatif de l'univers (en milliards d'ann�es) ?", new Reponse[] {
								new Reponse(1, "14"), 
								new Reponse(2, "25"),
								new Reponse(3, "2"),
								new Reponse(4, "60"),
						}),
						new Question(2, "Qui a observ� l'�loignement des galaxies grace au d�calage spectral ?", new Reponse[] {
								new Reponse(1, "Hubble"), 
								new Reponse(2, "Einstein"),
								new Reponse(3, "Planck"),
								new Reponse(4, "Hawking"),
						}),
						}),
		};
	}

	public String getNom() {
		return nom;
	}
}
