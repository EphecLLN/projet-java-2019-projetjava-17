/**
 *
 */
package projetQuizz.modele;

/**
 * @author Flo
 *
 */
public class Utilisateur {

    public static Utilisateur choisirOuCreer(String nom, int id) {
        // TODO créer ou récup en db si existant + faire un throw exception si le nom
        // est vide ou trop long
        return new Utilisateur(nom, id);
    }

    private String nom;
    private int id;

    public Utilisateur(String nom, int id) {
        super();
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
