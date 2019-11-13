**Membres :** Florent Theys,		Gauthier Verschraegen,		Edwin Autome
**Groupe :** 2TL1
***

# Cahier des charges : Groupe 17

## Description contexte

Dans le cadre du cours pratique de d�veloppement informatique orient�-objet, il est demand� aux �tudiants de cr�er un projet informatique bas� sur le langage JAVA.

## Objectif

Ce projet aura pour objectif de proposer une application fonctionnelle et bien document�e, cr��e � par un travail collaboratif notamment gr�ce � l�utilisation de la plateforme GitHub. 

## Contraintes techniques

Le projet devra respecter les contraintes suivantes :
* Respect d�une architecture MVC, permettant l�utilisation de deux interfaces utilisateurs (une interface console et une interface graphique).
* Pr�sence d�une base de donn�es, d�un service web ou d�une fonction multijoueur.
* Utilisation d�une structure de donn�es du Framework Java Collection.
* Impl�mentation de l�application via les outils de collaboration offerts par GitHub.

## Description des besoins

Le projet consiste dans le fait de d�velopper un jeu de type quizz.

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
* La place que le joueur a pris dans le classement global du th�me courant.
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
* Derni�re semaine de cours : remise du projet final.
projet-java-2019-projetjava-17
