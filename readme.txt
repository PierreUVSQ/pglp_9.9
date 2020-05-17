Le programme se compile avec mvn complie assembly:single
Il faut exéctuer le jar-with-dependencies.


Le programme lance ou crée une base de données.
On peut alors taper les ligne de commandes suivante:

c1 = Cercle((14,11),11)

t1 = Triangle((10,10),(11,11),(20,20))

r1 = Rectangle((115,100),100,500)

move(r1,(10,10))

group(test,(r1,c1))

remove(test)

save(dessin)

load(dessin)

delete(dessin)

quit()

Important:

- Les espaces ne comptent pas.
- save et load donnent un nom au dessin. On ne peut pas sauvegarder deux dessins avec le même nom.
- la commande move appliquée à une forme déplace la forme au point précis (le point a pour le triangle, en bas à gauche pour les carrés et rectangle)
- la commande move appliquée à un groupe la déplace dans la direction donné (addition par rapport à la direction courante)
- remove supprime un groupe ou une forme alors que delete est pour la sauvegarde