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
				new Theme(1, "Géograpie", new Question[] {
						new Question(1, "Quelle est la capitale de la Mongolie ?", new Reponse[] {
								new Reponse(1, "Oulan-Bator", true),
								new Reponse(2, "Kaboul", false),
								new Reponse(3, "Bagdad", false),
								new Reponse(4, "Douchanbé", false),
						}),
				}),
		};
	}

	public String getNom() {
		return nom;
	}
}
