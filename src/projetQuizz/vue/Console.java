package projetQuizz.vue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


import projetQuizz.Quizz;
import projetQuizz.QuizzException;
import projetQuizz.modele.*;
import projetQuizz.modele.Partie.CarreCash;
import projetQuizz.modele.Partie.Difficulte;
import projetQuizz.modele.Partie.Joker;

public class Console extends InterfaceDeJeu {

    private Scanner in;

    public Console(Quizz quizz) {
        super(quizz);
        in = new Scanner(System.in);
    }


    @Override
    public void afficherErreur(Exception e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void afficherResultat(Resultat resultat) throws Exception {
        if (resultat.getEstBonneReponse()) {
            System.out.println("Bravo ! Vous avez gagné " + resultat.getScore() + " points.");
        } else {
            System.out.println("Raté, la bonne réponse était: " + resultat.getBonneReponse().getReponse() + ".");
        }
        getQuizz().passerQuestionSuivante();
    }

    @Override
    public void afficherScores(Partie endedPartie) {
        Date today = Date.valueOf(LocalDate.now());
        int[] resultat = endedPartie.calculScore();
        System.out.println("Votre score final est de " + resultat[2] + " point(s) avec " + resultat[0]
                + " bonne(s) réponse(s) et " + resultat[1] + " mauvaise(s) réponse(s).");

        try {
            ResultSet rank = JDBCRequests.showCurrentRankTheme(resultat[0], endedPartie.getTheme().getId());
            ResultSet top = JDBCRequests.showTopTenTheme(endedPartie.getTheme().getId(), endedPartie.getNomDifficulte(endedPartie.getDifficulte()));

            System.out.println("Top 10 du thème " + JDBCRequests.getThemeNameById(endedPartie.getTheme().getId()) + " en difficulte " + endedPartie.getDifficulte() + "\n");
            System.out.println("\tTOP\t|\tUser\t\t|\tDate\t\t|\tScore\t");
            while (top.next()) {
                System.out.print("\t"+top.getInt("ROW_NUMBER() OVER (ORDER BY partie_score DESC)")+
                "\t|\t"+JDBCRequests.getUserNameById(top.getInt("utilisateur_id"))+"\t|\t"+top.getDate("dateEtHeure")+"\t|\t"+top.getInt("partie_score")+"\n");
            };
            while(rank.next()) {
                if (rank.getInt("partie_score") == resultat[2] && rank.getDate("dateEtHeure").before(today)) {
                    System.out.println("Votre partie a atteint le rang "
                            + rank.getInt("ROW_NUMBER() OVER (ORDER BY partie_score DESC)")
                            + " avec un score de " + rank.getInt("partie_score") + " points");
                    break;
                }
            }
            Connection connection = DriverManager.getConnection(endedPartie.url, endedPartie.login, endedPartie.passwd);
            Statement statement = connection.createStatement();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void choisirTheme(ArrayList<Theme> themesPossibles) throws Exception {
        System.out.println("Choisissez parmis ces thèmes:");
        for (int i = 0; i < themesPossibles.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + themesPossibles.get(i).getNom());
        }
        int index = demanderNombre(1, themesPossibles.size());
        getQuizz().recevoirTheme(themesPossibles.get(index -1));
    }

    @Override
    public void demanderCarreCash(Question questionActuelle) throws Exception {
        System.out.println(questionActuelle.getQuestion());
        System.out.println("Choisissez parmis:");
        System.out.println("1. carré");
        System.out.println("2. cash");
        int carreCash = demanderNombre(1, 2);
        getQuizz().recevoirCarreCash(carreCash == 1 ? CarreCash.CARRE : CarreCash.CASH);
    }

    @Override
    public void demanderDifficulte(Difficulte[] difficultes) throws Exception {
        System.out.println("Choisissez parmis ces difficultés:");
        for (int i = 0; i < difficultes.length; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + Partie.getNomDifficulte(difficultes[i]));
        }
        int index = demanderNombre(1, difficultes.length);
        getQuizz().recevoirDifficulte(difficultes[index - 1]);
    }

    @Override
    public void demanderMoiteMoite(Reponse[] reponsesPossiblesActuelles) throws Exception {
        System.out.println("Choisissez parmis ces possibilités:");
        for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + reponsesPossiblesActuelles[i].getReponse());
        }
        int index = demanderNombre(1, reponsesPossiblesActuelles.length);
        getQuizz().recevoirReponseMoiteMoite(reponsesPossiblesActuelles[index - 1]);
    }

    @Override
    public void demanderNom() throws Exception {
        String content;
        content = new String(Files.readAllBytes(Paths.get("regles.txt")));
        System.out.println(content);
        TimeUnit.SECONDS.sleep(7);

        String name = "";
        System.out.println("Quel est votre nom:");
        while (!JDBCRequests.checkUserIdentity(name = in.nextLine())) {
            System.out.println("Quel est votre nom:");
        }
        getQuizz().recevoirNomUtilisateur(JDBCRequests.getUserInfos(name));
    }

    /**
     * nextInt() plante en boucle si on lui donne un string, gestion d'erreur en
     * utilisant nextLine().
     */
    private int demanderNombre(int min, int max) throws QuizzException {
        int n;
        try {
            n = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            throw new QuizzException("Veuillez entrer un nombre.");
        }
        if (n < min || n > max) {
            throw new QuizzException("Veuillez choisir parmis les numéros proposés.");
        }
        return n;
    }

    @Override
    public void demanderReponseCarreJoker(Reponse[] reponsesPossiblesActuelles, Joker[] jokersPossibles)
            throws Exception {
        System.out.println("Choisissez parmis ces possibilités:");
        for (int i = 0; i < reponsesPossiblesActuelles.length; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + reponsesPossiblesActuelles[i].getReponse());
        }
        for (int i = 0; i < jokersPossibles.length; i++) {
            System.out.println(Integer.toString(i + 1 + reponsesPossiblesActuelles.length) + ". "
                    + Partie.getNomJokers(jokersPossibles[i]));

        }
        int index = demanderNombre(1, reponsesPossiblesActuelles.length + jokersPossibles.length);
        if (index <= reponsesPossiblesActuelles.length) {
            getQuizz().recevoirReponseCarre(reponsesPossiblesActuelles[index - 1]);
        } else {
            getQuizz().recevoirJoker(jokersPossibles[index - 1 - reponsesPossiblesActuelles.length]);
        }

    }

    @Override
    public void demanderReponseCash() throws Exception {
        System.out.println("Inscrivez votre réponse");
        getQuizz().recevoirReponseCash(in.nextLine());
    }
}