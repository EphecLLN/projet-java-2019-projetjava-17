package projetQuizz.modele;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gauthier Verschraegen
 */
class PartieTest {

    @org.junit.jupiter.api.Test
    void passerQuestionSuivante() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Partie partie = new Partie();
        partie.setTheme(new Theme(6,"Jeux"));
        partie.setQuestionsPossibles(JDBCRequests.getQuestionFromDB(6));
        int question = partie.getQuestionActuelle().getId();

        partie.setEtat(Partie.Etat.AFFICHER_RESULTAT_QUESTION);
        partie.passerQuestionSuivante();
        int questionSuivante = partie.getQuestionActuelle().getId();
        assertNotEquals(question,questionSuivante);

        Partie partie2 = new Partie();
        partie2.setTheme(new Theme(6,"Jeux"));
        partie.setQuestionsPossibles(JDBCRequests.getQuestionFromDB(6));
        int question2 = partie.getQuestionActuelle().getId();

        partie2.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
        partie2.recevoirDifficulte(Partie.Difficulte.FACILE);
        int questionSuivante2 = partie2.getQuestionActuelle().getId();
        assertEquals(question2,questionSuivante2);


    }

    @org.junit.jupiter.api.Test
    void recevoirCarreCash() {
    }

    @org.junit.jupiter.api.Test
    void recevoirDifficulte() {
    }

    @org.junit.jupiter.api.Test
    void recevoirNomUtilisateur() {
    }

    @org.junit.jupiter.api.Test
    void recevoirReponseCarre() {
    }

    @org.junit.jupiter.api.Test
    void recevoirReponseCash() {
    }

    @org.junit.jupiter.api.Test
    void recevoirReponseMoiteMoite() {
    }

    @org.junit.jupiter.api.Test
    void recevoirTheme() {
    }

    @org.junit.jupiter.api.Test
    void utiliserJoker() {
    }

    @org.junit.jupiter.api.Test
    void simpleCase() {
    }

    @org.junit.jupiter.api.Test
    void isNum() {
    }

    @org.junit.jupiter.api.Test
    void calculScore() {
    }
}