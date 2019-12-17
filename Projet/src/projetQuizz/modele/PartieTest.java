package projetQuizz.modele;

import java.lang.reflect.Array;
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
        //------------------------------------------------------------------------
        Partie partie2 = new Partie();
        partie2.setTheme(new Theme(6,"Jeux"));
        partie2.setQuestionsPossibles(JDBCRequests.getQuestionFromDB(6));
        int question2 = partie2.getQuestionActuelle().getId();

        partie2.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
        partie2.recevoirDifficulte(Partie.Difficulte.FACILE);
        int questionSuivante2 = partie2.getQuestionActuelle().getId();
        assertEquals(question2,questionSuivante2);
        //------------------------------------------------------------------------
        Partie partie3 = new Partie();
        partie3.setTheme(new Theme(1,"Géographie"));
        partie3.setQuestionsPossibles(JDBCRequests.getQuestionFromDB(6));
        int question3 = partie3.getQuestionActuelle().getId();

        partie3.setEtat(Partie.Etat.AFFICHER_RESULTAT_QUESTION);
        partie3.passerQuestionSuivante();
        int questionSuivante3 = partie3.getQuestionActuelle().getId();
        assertNotEquals(question3,questionSuivante3);
        //------------------------------------------------------------------------
        Partie partie4 = new Partie();
        partie4.setTheme(new Theme(1,"Géographie"));
        partie4.setQuestionsPossibles(JDBCRequests.getQuestionFromDB(6));
        int question4 = partie4.getQuestionActuelle().getId();

        partie4.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
        partie4.recevoirDifficulte(Partie.Difficulte.FACILE);
        int questionSuivante4 = partie4.getQuestionActuelle().getId();
        assertEquals(question4,questionSuivante4);

        assertNotEquals(question,0);
        assertNotEquals(question, "");

        assertNotEquals(question2,0);
        assertNotEquals(question2, "");

        assertNotEquals(question3,0);
        assertNotEquals(question3, "");

        assertNotEquals(question4,0);
        assertNotEquals(question4, "");

    }

    @org.junit.jupiter.api.Test
    void recevoirNomUtilisateur() {
        Partie partie = new Partie();
        partie.setEtat(Partie.Etat.DEMANDER_LE_NOM);
        String nom = "test";
        try {
            partie.recevoirNomUtilisateur(nom);
            assertEquals(partie.getUtilisateur().getNom(),nom);
            nom = "1";
            partie.recevoirNomUtilisateur(nom);
            assertEquals(partie.getUtilisateur().getNom(),nom);
            nom = "";
            partie.recevoirNomUtilisateur(nom);
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @org.junit.jupiter.api.Test
    void recevoirReponseCarre() throws Exception {
        Partie partie = new Partie();
        partie.setEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER);
        ArrayList<Reponse> reponses = new ArrayList<Reponse>();
        ArrayList<Question> questions = new ArrayList<Question>();
        reponses.add(new Reponse(1,"a",true));
        reponses.add(new Reponse(2,"b",false));
        reponses.add(new Reponse(3,"c",false));
        reponses.add(new Reponse(4,"d",false));
        questions.add(new Question(1,"je suis a",reponses));

        partie.setQuestionsPossibles(questions);
        partie.recevoirReponseCarre(reponses.get(0));
        int points = partie.calculScore()[2];
        assertEquals(1,points);
        assertNotEquals(0,points);
        assertNotEquals("",points);

        Partie partie2 = new Partie();
        partie2.setEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER);
        partie2.setQuestionsPossibles(questions);
        partie2.recevoirReponseCarre(reponses.get(1));
        int points2 = partie2.calculScore()[2];
        assertEquals(0,points2);
        assertNotEquals(1,points2);

    }

    @org.junit.jupiter.api.Test
    void recevoirReponseCash() throws Exception {
        Partie partie = new Partie();
        partie.setEtat(Partie.Etat.DEMANDER_REPONSE_CASH);
        ArrayList<Reponse> reponses = new ArrayList<Reponse>();
        ArrayList<Question> questions = new ArrayList<Question>();
        reponses.add(new Reponse(1,"a",true));
        reponses.add(new Reponse(2,"b",false));
        reponses.add(new Reponse(3,"c",false));
        reponses.add(new Reponse(4,"d",false));
        questions.add(new Question(1,"je suis a",reponses));
        partie.setQuestionsPossibles(questions);
        partie.setReponsesPossiblesActuelles(reponses);
        partie.recevoirReponseCash("a");
        int points = partie.calculScore()[2];
        assertEquals(3,points);
        assertNotEquals(0,points);
        assertNotEquals("",points);

        Partie partie2 = new Partie();
        partie2.setEtat(Partie.Etat.DEMANDER_REPONSE_CASH);
        partie2.setQuestionsPossibles(questions);
        partie2.setReponsesPossiblesActuelles(reponses);
        partie2.recevoirReponseCash("b");
        int points2 = partie2.calculScore()[2];
        assertEquals(0,points2);
        assertNotEquals(3,points2);
        assertNotEquals("",points2);
    }
    @org.junit.jupiter.api.Test
    void utiliserJoker() throws Exception {
        Partie partie = new Partie();
        partie.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
        partie.setDifficulte(Partie.Difficulte.FACILE);
        partie.setEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER);
        ArrayList<Reponse> reponses = new ArrayList<Reponse>();
        ArrayList<Question> questions = new ArrayList<Question>();
        reponses.add(new Reponse(1,"a",true));
        reponses.add(new Reponse(2,"b",false));
        reponses.add(new Reponse(3,"c",false));
        reponses.add(new Reponse(4,"d",false));
        questions.add(new Question(1,"je suis a",reponses));
        partie.setQuestionsPossibles(questions);
        partie.setReponsesPossiblesActuelles(reponses);
        partie.utiliserJoker(Partie.Joker.MOITE_MOITE);
        assertEquals(2,partie.getReponsesPossiblesActuelles().length);
        assertNotEquals(4,partie.getReponsesPossiblesActuelles().length);
        assertNotEquals("",partie.getReponsesPossiblesActuelles().length);

        Partie partie2 = new Partie();
        partie2.setEtat(Partie.Etat.DEMANDER_LA_DIFFCULTE);
        partie2.setDifficulte(Partie.Difficulte.FACILE);
        partie2.setQuestionsPossibles(questions);
        partie2.setReponsesPossiblesActuelles(reponses);
        partie2.setEtat(Partie.Etat.DEMANDER_REPONSE_CARRE_OU_JOKER);
        partie2.utiliserJoker(Partie.Joker.DONNE_MON_POINT);
        assertEquals(1,partie2.calculScore()[2]);
        assertNotEquals(3,partie2.calculScore()[2]);
        assertNotEquals("",partie2.calculScore()[2]);
    }

    @org.junit.jupiter.api.Test
    void simpleCase() {
        assertEquals("aeou",Partie.simpleCase("àéôù"));
        assertEquals("ceeu",Partie.simpleCase("çèêû"));
        assertEquals("cava",Partie.simpleCase("cava"));
        assertNotEquals(0,Partie.simpleCase("0"));
        assertNotEquals("àéôù",Partie.simpleCase("àéôù"));
    }

    @org.junit.jupiter.api.Test
    void isNum() {
        assertTrue(Partie.isNum("0"));
        assertTrue(Partie.isNum("1"));
        assertTrue(Partie.isNum("11"));
        assertFalse(Partie.isNum("cava"));
        assertFalse(Partie.isNum(""));
    }
}