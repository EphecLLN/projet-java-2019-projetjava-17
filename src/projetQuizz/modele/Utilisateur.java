/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Utilisateur {

	public static Utilisateur choisirOuCreer(String nom) {
		// TODO cr�er ou r�cup en db si existant + faire un throw exception si le nom
		// est vide ou trop long
		return new Utilisateur(nom);
	}

	private String nom;

	public Utilisateur(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
