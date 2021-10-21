#include <stdlib.h>
#include <stdio.h>
#include <omp.h>

#define ALIVE 1
#define DEAD 0

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
	int up, down, left, right, count;

	up = i == 0 ? N-1 : i-1;
	down = i == N-1 ? 0 : i + 1;
	left = j == 0 ? N - 1: j - 1;
	right = j == N - 1 ? 0 : j+1;

	count = 0;
	count += grid[up][j];
	count += grid[up][right];
	count += grid[i][right];
	count += grid[down][right];
	count += grid[down][j];
	count += grid[down][left];
	count += grid[i][left];
	count += grid[up][left];

	return count;
}

int main(void){
	int N, geracoes;
	int i, j;
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
	for(i=0; i<N; i++)
		for(j=0; j<N; j++)
			grid[i][j] = DEAD;

	// Configuracao inicial do tabuleiro
	startGrid(grid, N);

	for(i=0; i<N; i++){
		for(j=0; j<N; j++)
			printf("%d ", grid[i][j]);
		printf("\n");
	}

	// Liberacao da matriz NxN
	for(i=0; i<N; i++){
		free(grid[i]);
		free(newgrid[i]);
	}
	free(grid);
	free(newgrid);

	return 0;
}