**Membres :** Florent Theys,		Gauthier Verschraegen,		Edwin Autome
**Groupe :** 2TL1
***

# Cahier des charges : Groupe 17

## Description contexte

old: Dans le cadre du cours pratique de développement informatique orienté-objet, il est demandé aux étudiants de créer un projet informatique basé sur le langage JAVA.

new: Ce quizz s'addresse à tous le monde, aussi bien à celui ou celle qui souhaite se divertir ou encore, élargir/tester ses connaissances sur les sujets que propose le quizz. C'est sujet sont la géographie, l'espace, l'histoire, les langues, la musique et les jeux.

## Objectif

old: Ce projet aura pour objectif de proposer une application fonctionnelle et bien documentée, créée par un travail collaboratif notamment grâce à l'utilisation de la plateforme GitHub. 

new :Ce quizz a pour objectif de permettre à ses utilisateurs d'apprendre en s'amusant ou de simplement se divertir le temps d'un quizz sur l'un des thèmes proposé par celui-ci.

## Contraintes techniques

old:
Le projet devra respecter les contraintes suivantes :
* Respect d'une architecture MVC, permettant l'utilisation de deux interfaces utilisateurs (une interface console et une interface graphique).
* Présence d'une base de données, d'un service web ou d'une fonction multijoueur.
* Utilisation d'une structure de données du Framework Java Collection.
* Implémentation de l'application via les outils de collaboration offerts par GitHub.

new:
Le projet devra respecter les contraintes suivantes :
* Respect d'une architecture MVC, permettant l'utilisation de deux interfaces utilisateurs (une interface console et une interface graphique).
* Utilisation d'une structure de données du Framework Java Collection.
* Implémentation de l'application via les outils de collaboration offerts par GitHub.
* Le serveur contenant la base de données étant lancé sur une machine local, le fichier d'import de la base de données doit être mis à jour et envoyé sur le repo GitHub à la fin de chaque partie ou session de jeu afin que les diverses fonctionnalités requierant une base de données à jour renvoient des valeurs cohérentes
* Utilisation de JDBC (Java DataBase Connectivity) permettant de se connecter et d'interagir avec la base de données

## Description des besoins

old: Le projet consiste dans le fait de développer un jeu de type quizz.

new: User stories ???

### Démarrage du quizz :

* Première étape : le joueur devra encoder un nom d'utilisateur.
* Deuxième étape : le joueur aura le choix entre six thèmes différents.
* Troisième étape : l'utilisateur aura le choix entre quatre niveaux de difficulté.

### Déroulement du quizz : 

Il sera proposé aléatoirement dix questions différentes au joueur parmis un ensemble de 15 questions uniques.
L'utilisateur devra donc essayer de répondre correctement à chaque question posée. Deux choix s'offrent à lui, chacune rapportant un certain nombre de points :
* écrire sa réponse manuellement, offrant le plus grand nombre de point.
* Choisir une réponse parmis quatre proposées à l'écran, offrant moins de points. Un joker ne peut être utilisé qu'une fois arrivé dans cette interface.

Une mauvaise réponse ne rapporte pas de points.

### Fin de partie : 

Plusieurs informations seront affichées en fin de partie :
* Un classement des 10 meilleurs personnes dans le thème courant.
* La place que le joueur a pris dans le classement global du thème courant. //Heu on implémente ou pas ?
* Le nombre de questions réussies/ratées.
Les deux premiers points expliqués ci-dessus seront fournis et mis à jour via une base de données.

### Principe des jokers: 

Il y aura un système de trois jokers mis en place :
* Le "Moite/Moite" : Deux propositions incorrectes sont supprimées parmis les quatres disponibles. Donne un point si la bonne réponse est trouvée.
* Le "Change moi ça, j'aime pas ta question" : Permet à l'utilisateur de changer de question.
* Le "J'en sais rien, donne moi mon point" : Permet de réussir la question au prix d'un seul point gagné.

Chaque mode de difficulté offre un certain nombre de joker :
* En mode "facile", les trois jokers seront utilisables.
* En mode "normal", l'utilisateur aura accès au "Moite/Moite" et au "Change moi ça, j'aime pas ta question".
* En mode "difficile", le joueur ne pourra bénéficier que du "Moite/Moite".
* En mode "expert", aucun joker n'est à la disposition de l'utilisateur.

## Organisation du projet

### Les impératifs temporels : 
* 25 octobre 2019 : remise du cahier des charges.
* 8 novembre 2019 : remise du diagramme de classe UML.
* 15 novembre 2019 : remise de l'implémentation d'une classe par étudiant.
* 29 novembre 2019 : démo des interactions possibles du projet en interface console.
* 17 décembre 2019 : remise du projet final.
* 20 décembre 2019 : Défense du projet
