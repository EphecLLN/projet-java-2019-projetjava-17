package projetQuizz.modele;

public class Utilisateur {

	private String nom;
	private int id;

	/**
	 * Constructeur de la classe Utilisateur.
	 */
	public Utilisateur(String nom, int id) {
		super();
		this.nom = nom;
		this.id = id;
	}

	/**
	 * Getter de nom.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter de nom.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de id.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setter de id.
	 */
	public void setId(int id) {
		this.id = id;
	}

}