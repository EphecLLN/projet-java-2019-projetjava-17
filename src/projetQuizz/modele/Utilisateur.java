/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Utilisateur {

	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Utilisateur(String nom) {
		super();
		this.nom = nom;
	}

	public static Utilisateur choisirOuCreer(String nom) {
		// TODO créer ou récup en db si existant + faire un throw exception si le nom est vide ou trop long
		return new Utilisateur(nom);
	}

}
