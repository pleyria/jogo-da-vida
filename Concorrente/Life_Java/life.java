/* Game of Life
 * Linguagem Java
 * Concorrente */

import java.util.Scanner;

public class life{
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

	/* Recebe um grid e um newgrid NxN.
 	 * Simula uma geracao atualizando as celular de newgrid. */
	static void simulate(int grid[][], int newgrid[][], int N, int T){
		int i, j, neighbors, div;
		lifeThread[] lifeTh; // Thread

		// numero de iteracoes para cada thread
		div = N/T;

		// Instancias das T threads
		lifeTh = new lifeThread[T];
		for(i=0; i<T-1; i++){
			lifeTh[i] = new lifeThread(grid, newgrid, i*div, (i+1)*div-1, N);
		}
		// A ultima thread pode receber o resto se o numero de linhas
		// nao for multiplo do numero de threads escolhido
		lifeTh[T-1] = new lifeThread(grid, newgrid, i*div, (i+1)*div-1 + (N % T), N);

		// Execucao das threads
		for(i=0; i<T; i++)
			lifeTh[i].start();

		// Espera todas as threads terminarem
		try {
            for(i=0; i<T; i++)
            	lifeTh[i].join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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
		int N, G, T; // parametros da simulacao
		int i, j, k; // variaveis de controle
		int[][] grid, newgrid; // estruturas do grid
		long start, end, start_g, end_g; // contagem de tempo

		Scanner teclado = new Scanner(System.in);

		// Leitura dos parametros da simulacao
		System.out.println("Dimensao do tabuleiro (NxN)?");
		N = teclado.nextInt();
		System.out.println("Numero de geracoes (iteracoes)?");
		G = teclado.nextInt();
		System.out.println("Numero de threads (1, 2, 4, 8)?");
		T = teclado.nextInt();
		teclado.close();

		start = System.currentTimeMillis();

		// Alocacao das matrizes NxN
		grid = new int[N][N];
		newgrid = new int[N][N];

		// Configuracao inicial do tabuleiro
		startGrid(grid, N);

		System.out.println("\nIniciando simulacao...\n");
		//System.out.printf("Condicao inicial: %d vivos.\n", countAlive(grid, N));

		// Realiza a simulacao para G geracoes
		// Alterna a posicao de grid e newgrid nas chamadas
		// (G impar -> resposta final em newgrid)
		// (G par -> resposta final em grid)
		start_g = System.currentTimeMillis();
		for(i=0; i<G; i++){
			if(i % 2 == 0){
				simulate(grid, newgrid, N, T);
				System.out.printf("Geracao %d: %d vivos\n", i+1, countAlive(newgrid, N));
			}
			else{
				simulate(newgrid, grid, N, T);
				System.out.printf("Geracao %d: %d vivos\n", i+1, countAlive(grid, N));
			}
		}
		end_g = System.currentTimeMillis();
		System.out.printf("\nSimulacao Finalizada.\n\n");

		end = System.currentTimeMillis();

		System.out.printf("Tempo total decorrido: %f segundos.\n", (end-start)/1000.0);
		System.out.printf("Tempo para computacao das geracoes: %f segundos.\n", (end_g-start_g)/1000.0);
	}
}