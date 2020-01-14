#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NMAX 100
typedef struct { //graphe représenté par une matrice d'adjacence
  	int nombre_sommet;
  	int ** matrice_adjacence;
} graphe;

typedef struct pile pile; 
 
struct pile{
	int val;
	pile* suiv;
};



struct tableau{ //tableau de degre
	int tab[NMAX];
};
typedef struct tableau Tableau;


void affiche_graphe(graphe G){
	int i,j;
	printf("Graphe avec %d sommets \n",G.nombre_sommet);
	for(i = 0; i<G.nombre_sommet; i++){
		printf("Voisins de %d: ",i);
		for(j = 0; j < G.nombre_sommet; j++){
			if(G.matrice_adjacence[i][j]==1) printf("%d ",j);
		}
		printf("\n");
	}
}

int degre(graphe G, int i){
	int som = 0;
	for(int j=0; j<G.nombre_sommet ;j++){
		som += G.matrice_adjacence[i][j];
	}
	return som;
}

void tabDegre(Tableau *t,graphe G){
	for(int i=0;i<G.nombre_sommet;i++)
		t->tab[i]=degre(G,i);
}

graphe init_graphe(int n){//créé un graphe dont tous les sommets sont isolés
	graphe G; 
	G.nombre_sommet = n;
	G.matrice_adjacence = malloc(sizeof(int*) * n);
	for(int i=0; i<n; i++){
		G.matrice_adjacence[i] = malloc(sizeof(int) * n);
		for(int j=0; j<n; j++){
			G.matrice_adjacence[i][j] = 0;
		}
	}
	return G;
}

void libere_graphe(graphe G){
	for(int i=0; i<G.nombre_sommet; i++){
		free(G.matrice_adjacence[i]);
	}
	free(G.matrice_adjacence);
}


graphe remplissageGraphe(int n){
	graphe G = init_graphe(n);
	FILE* fichier = NULL;
    int c = 0;
    int c1,c2;
    fichier = fopen("graphes", "r");
 
    if (fichier != NULL)
    {
        while((c = fgetc(fichier)) != EOF) // Attention parenthèses
		{
			if(c > 45){
				c1=c;
				fgetc(fichier);
				fgetc(fichier);
				fgetc(fichier);
				c = fgetc(fichier);
				if(c > 45){
					c2 = c;
					G.matrice_adjacence[c1 - 65][c2 - 65] = 1;
					G.matrice_adjacence[c2 - 65][c1 - 65] = 1;
				}
			}
		}
			
        fclose(fichier);
        return G;
    }
 return G;
}

//fonction qui retourne l'indice du max degré
int Imax(Tableau *t,int i)
{
	int iMax=i;
	int d=i;
	for(d=i+1;d<20;d++)
	{
		if((*t).tab[iMax]<(*t).tab[d])
		{
			iMax=d;
		}
	}
	return iMax;
}

void miseAjourGraphe(graphe G,Tableau *t,int j){
	for(int i=0;i<G.nombre_sommet;i++)
	{
		G.matrice_adjacence[i][j] = 0;
		G.matrice_adjacence[j][i] = 0;
	}
	tabDegre(t,G);
}

int estVide(graphe G){
	for(int i=0; i<G.nombre_sommet; i++){
		for(int j=0; j<G.nombre_sommet; j++){
			if(G.matrice_adjacence[i][j] != 0)
				return 0;
		}
	}
	return 1;
}

int main(){
	/* Tests pour vérifier si vos implémentations sont correctes*/
	
	/*srand(time(NULL));
	graphe G = alea_graphe(10,0.2);*/
	Tableau t;
	graphe G = remplissageGraphe(20);
	int x=0;
	int count=0;
	tabDegre(&t,G);
	//affiche_graphe(G);
	/*for(int i=0;i<20;i++)
		printf("%d a pour dégré : %d\n",i,t.tab[i]);
	printf("%d est l'indice du max\n",Imax(&t,0));
	miseAjourGraphe(G,&t,Imax(&t,0));
	printf("\n\n\n");
	for(int i=0;i<20;i++)
		printf("%d a pour dégré : %d\n",i,t.tab[i]);
	printf("%d est l'indice du max\n",Imax(&t,0));*/
	
	while(estVide(G) != 1)
	{
		x = Imax(&t,0);
		miseAjourGraphe(G,&t,x);
		count++;
		printf("le choix n°%d : %c \n",count,65+x);
	}
	
	libere_graphe(G);
	
	
  	
}
