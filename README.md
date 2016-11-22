# Projet Citadelles

Ce projet implémente en version textuelle (aucune IHM) le jeu de société Citadelles.

## Regles du jeu

Les regles du jeu sont disponnibles à l'adresse suivante : [http://www.jeuxavolonte.asso.fr/regles/citadelles_seconde_edition.pdf](http://www.jeuxavolonte.asso.fr/regles/citadelles_seconde_edition.pdf)

## Implémentation du jeu

Le programme présenté implémente la seconde édition du jeu Citadelles dans une partie concue pour deux joueurs uniquement. 

Le projet se lance via le main présent dans la classe Application du package ApplicationTests.

Le jeu se déroule entièrement via une saisie des différents utilisateurs via la console. Le joueur entre les numéros correspondants à ses choix dans la console.
- Lorsque plusieurs choix sont proposés avec un numéro qui précède chaque choix, le joueur doit rentrer le numéro correspondant à son choix.
- Lorsqu'une liste apparait et qu'aucun numéro n'indique les numéro des choix à saisir, alors **la liste démarre au numéro 0** et le joueur doit compter jusqu'à l'élément qui l'interesse et rentrer son numéro.

## Documentation

Une documentation en Javadoc est disponnible et permet d'avoir une indication sur l'utilisation et les appekls possibles pour chaque classe implémentée.

Un diagramme UML a également été généré. Il est visible et éditable via le plugin eclipse ObjectAid UML.
Ci-dessous une version png du diagramme :

<img src="doc/uml.png">

## IHM

Le projet Citadelle est effectué en deux parties. Ce code correspond à la première partie (implémentation du jeu) et va être ré-utilisé afin de produire une IHM ainsi qu'une IA permettant à un joueur de jouer seul face à un ordinateur. 

Pour l'implémentation de l'IHM, une copie des images du jeu a été effectuée et est disponnible dans le dossier 'images' du projet.

## Crédit

Ce projet a été développé pour la filière Ingénierie Logicielle de l'Ecole des Mines de Nantes dans la cadre de l'UV "Actualisation des Compétences en Développement et Conception".