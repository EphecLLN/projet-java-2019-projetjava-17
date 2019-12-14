**Membres :** Florent Theys,		Gauthier Verschraegen,		Edwin Autome
**Groupe :** 2TL1
***

# Cahier des charges : Groupe 17

## Description contexte

old: Dans le cadre du cours pratique de d�veloppement informatique orient�-objet, il est demand� aux �tudiants de cr�er un projet informatique bas� sur le langage JAVA.

new: Ce quizz s'addresse à tous le monde, aussi bien à celui ou celle qui souhaite se divertir ou encore, élargir/tester ses connaissances sur les sujets que propose le quizz. C'est sujet sont la géographie, l'espace, l'histoire, les langues, la musique et les jeux.

## Objectif

old: Ce projet aura pour objectif de proposer une application fonctionnelle et bien document�e, cr��e � par un travail collaboratif notamment gr�ce � l�utilisation de la plateforme GitHub. 

new :Ce quizz a pour objectif de permettre à ses utilisateurs d'apprendre en s'amusant ou de simplement se divertir le temps d'un quizz sur l'un des thèmes proposé par celui-ci.

## Contraintes techniques

old:
Le projet devra respecter les contraintes suivantes :
* Respect d�une architecture MVC, permettant l�utilisation de deux interfaces utilisateurs (une interface console et une interface graphique).
* Pr�sence d�une base de donn�es, d�un service web ou d�une fonction multijoueur.
* Utilisation d�une structure de donn�es du Framework Java Collection.
* Impl�mentation de l�application via les outils de collaboration offerts par GitHub.

new:
Le projet devra respecter les contraintes suivantes :
* Respect d�une architecture MVC, permettant l�utilisation de deux interfaces utilisateurs (une interface console et une interface graphique).
* Utilisation d�une structure de donn�es du Framework Java Collection.
* Impl�mentation de l�application via les outils de collaboration offerts par GitHub.
* Le serveur contenant la base de données étant lancé sur une machine local, le fichier d'import de la base de données doit être mis à jour et envoyé sur le repo GitHub à la fin de chaque partie ou session de jeu afin que les diverses fonctionnalités requierant une base de données à jour renvoient des valeurs cohérentes
* Utilisation de JDBC (Java DataBase Connectivity) permettant de se connecter et d'interagir avec la base de données

## Description des besoins

old: Le projet consiste dans le fait de d�velopper un jeu de type quizz.

new: User stories ???

### D�marrage du quizz :

* Premi�re �tape : le joueur devra encoder un nom d�utilisateur.
* Deuxi�me �tape : le joueur aura le choix entre six th�mes diff�rents.
* Troisi�me �tape : l�utilisateur aura le choix entre quatre niveaux de difficult�.

### D�roulement du quizz : 

Il sera propos� al�atoirement dix questions diff�rentes au joueur parmis un ensemble de 15 questions uniques.
L�utilisateur devra donc essayer de r�pondre correctement � chaque question pos�e. Deux choix s�offrent � lui, chacune rapportant un certain nombre de points :
* �crire sa r�ponse manuellement, offrant le plus grand nombre de point.
* Choisir une r�ponse parmis quatre propos�es � l��cran, offrant moins de points. Un joker ne peut �tre utilis� qu�une fois arriv� dans cette interface.

Une mauvaise r�ponse ne rapporte pas de points.

### Fin de partie : 

Plusieurs informations seront affich�es en fin de partie :
* Un classement des 10 meilleurs personnes dans le th�me courant.
* La place que le joueur a pris dans le classement global du th�me courant. //Heu on implémente ou pas ?
* Le nombre de questions r�ussies/rat�es.
Les deux premiers points expliqu�s ci-dessus seront fournis et mis � jour via une base de donn�es.

### Principe des jokers: 

Il y aura un syst�me de trois jokers mis en place :
* Le �Moite/Moite� : Deux propositions incorrectes sont supprim�es parmis les quatres disponibles. Donne un point si la bonne r�ponse est trouv�e.
* Le �Change moi �a, j�aime pas ta question� : Permet � l�utilisateur de changer de question.
* Le �J�en sais rien, donne moi mon point� : Permet de r�ussir la question au prix d�un seul point gagn�.

Chaque mode de difficult� offre un certain nombre de joker :
* En mode �facile�, les trois jokers seront utilisables.
* En mode �normal�, l�utilisateur aura acc�s au �Moite/Moite� et au �Change moi �a, j�aime pas ta question�.
* En mode �difficile�, le joueur ne pourra b�n�ficier que du �Moite/Moite�.
* En mode �expert�, aucun joker n�est � la disposition de l�utilisateur.

## Organisation du projet

### Les imp�ratifs temporels : 
* 25 octobre 2019 : remise du cahier des charges.
* 8 novembre 2019 : remise du diagramme de classe UML.
* 15 novembre 2019 : remise de l�impl�mentation d�une classe par �tudiant.
* 29 novembre 2019 : d�mo des interactions possibles du projet en interface console.
* 17 décembre 2019 : remise du projet final.
* 20 décembre 2019 : Défense du projet
