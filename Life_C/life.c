#include <stdlib.h>
#include <stdio.h>
#include <omp.h>

/* Recebe um grid NxN.
 * Insere um glider a partir da posicao (0,0).
 * Insere um R-pentomino a partir da posicao (10, 30). */
void startGrid(int** grid, int N){
	//GLIDER
	int lin = 1, col = 1;
	grid[lin  ][col+1] = 1;
	grid[lin+1][col+2] = 1;
	grid[lin+2][col  ] = 1;
	grid[lin+2][col+1] = 1;
	grid[lin+2][col+2] = 1;

	//R-pentomino
	lin =10; col = 30;
	grid[lin  ][col+1] = 1;
	grid[lin  ][col+2] = 1;
	grid[lin+1][col  ] = 1;
	grid[lin+1][col+1] = 1;
	grid[lin+2][col+1] = 1;
}

/* Recebe um grid NxN.
 * Retorna o numero de vizinhos vivos de (i, j). */
int getNeighbors(int** grid, int i, int j, int N){

}

int main(void){
	int N, geracoes;
	int i;
	int **grid, **newgrid;

	// Leitura dos parametros
	printf("Dimensao do tabuleiro?\n");
	scanf("%d", &N);
	printf("Numero de geracoes?\n");
	scanf("%d", &geracoes);

	// Alocacao da matriz NxN
	grid = (int**) malloc(N * sizeof(int*));
	newgrid = (int**) malloc(N * sizeof(int*));
	for(i=0; i<N; i++){
		grid[i] = (int*) malloc(N * sizeof(int));
		newgrid[i] = (int*) malloc(N * sizeof(int));
	}

	// Configuracao inicial do tabuleiro
	startGrid(grid, N);

	// Liberacao da matriz NxN
	for(i=0; i<N; i++){
		free(grid[i]);
		free(newgrid[i]);
	}
	free(grid);
	free(newgrid);

	return 0;
}