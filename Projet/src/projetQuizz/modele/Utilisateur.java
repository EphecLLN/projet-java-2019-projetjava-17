package projetQuizz.modele;

public class Utilisateur {

	private String nom;
	private int id;

	/**
	 * Constructeur de la classe Utilisateur.
	 * @param nom le nom de l'utilisateur
	 * @param id l'id de l'utilisateur en db
	 */
	public Utilisateur(String nom, int id) {
		super();
		this.nom = nom;
		this.id = id;
	}

	/**
	 * Getter de nom.
	 * @return le nom de l'utilisateur
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter de nom.
	 * @param nom le nom d'utilisateur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de id.
	 * @return l'id de l'utilisateur
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setter de id.
	 * @param id l'id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
