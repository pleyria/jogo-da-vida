/* HighLife
 * Linguagem Java
 * Serial */

import java.util.Scanner;

public class highLife{
	/* Recebe um grid NxN.
	 * Insere um glider a partir da posicao (0,0).
	 * Insere um R-pentomino a partir da posicao (10, 30). */
	static void startGrid(int grid[][], int N){
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
	static int getNeighbors(int grid[][], int i, int j, int N){
		int up, down, left, right, count;

		// Define posicoes vizinhas respeitando os
		// limites do tabuleiro
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

	/* Recebe um grid e um newgrid NxN.
 	 * Simula uma geracao atualizando as celular de newgrid. */
	static void simulate(int grid[][], int newgrid[][], int N){
		int i, j, neighbors;

		for(i=0; i<N; i++){
			for(j=0; j<N; j++){
				neighbors = getNeighbors(grid, i, j, N);

				// 1) Celula viva com 2 ou 3 vizinhos deve sobreviver
				if(grid[i][j] == 1 && (neighbors == 2 || neighbors == 3))
					newgrid[i][j] = 1;

				// 2) Celula morta com 3 ou 6 vizinhos torna-se viva
				else if(grid[i][j] == 0 && (neighbors == 3 || neighbors == 6))
					newgrid[i][j] = 1;

				// 3) Qualquer outro caso
				else{
					// celulas vivas devem morrer
					// celulas mortas continuam mortas
					newgrid[i][j] = 0;
				}
			}
		}
	}

	/* Recebe um grid NxN.
 	 * retorna o numero de celulas vivas no tabuleiro. */
	static int countAlive(int grid[][], int N){
		int i, j, count;

		count = 0;

		for(i = 0; i < N; i++)
			for(j = 0; j < N; j++)
				count += grid[i][j];

		return count;
	}

	public static void main(String[] args){
		int N, G; // parametros da simulacao
		boolean exibir;
		int i, j, k; // variaveis de controle
		int[][] grid, newgrid; // estruturas do grid
		long start, end, start_g, end_g; // contagem de tempo

		Scanner teclado = new Scanner(System.in);

		// Leitura dos parametros da simulacao
		System.out.println("Dimensao do tabuleiro (NxN)?");
		N = teclado.nextInt();
		System.out.println("Numero de geracoes (iteracoes)?");
		G = teclado.nextInt();
		System.out.println("Mostrar matriz 50x50? 1 = sim, 0 = nao.");
		exibir = teclado.nextInt() == 1;
		teclado.close();

		start = System.currentTimeMillis();

		// Alocacao das matrizes NxN
		grid = new int[N][N];
		newgrid = new int[N][N];

		// Configuracao inicial do tabuleiro
		startGrid(grid, N);

		System.out.println("\nIniciando simulacao...\n");
		System.out.printf("Condicao inicial: %d vivos.\n", countAlive(grid, N));
		if(exibir){
			for(j=0; j<50; j++){
				for(k=0; k<50; k++)
					System.out.printf("%d ", grid[j][k]);
				System.out.printf("\n");
			}
		}

		// Realiza a simulacao para G geracoes
		// Alterna a posicao de grid e newgrid nas chamadas
		// (G impar -> resposta final em newgrid)
		// (G par -> resposta final em grid)
		start_g = System.currentTimeMillis();
		for(i=0; i<G; i++){
			if(i % 2 == 0){
				simulate(grid, newgrid, N);
				System.out.printf("Geracao %d: %d vivos\n", i+1, countAlive(newgrid, N));
				if(exibir){
					for(j=0; j<50; j++){
						for(k=0; k<50; k++)
							System.out.printf("%d ", newgrid[j][k]);
						System.out.printf("\n");
					}
				}
			}
			else{
				simulate(newgrid, grid, N);
				System.out.printf("Geracao %d: %d vivos\n", i+1, countAlive(grid, N));
				if(exibir){
					for(j=0; j<50; j++){
						for(k=0; k<50; k++)
							System.out.printf("%d ", grid[j][k]);
						System.out.printf("\n");
					}
				}
			}
		}
		end_g = System.currentTimeMillis();
		System.out.printf("\nSimulacao Finalizada.\n\n");

		end = System.currentTimeMillis();

		System.out.printf("Tempo total decorrido: %f segundos.\n", (end-start)/1000.0);
		System.out.printf("Tempo para computacao das geracoes: %f segundos.\n", (end_g-start_g)/1000.0);
	}
}