/* Game of Life
 * Linguagem Java
 * Concorrente */

public class lifeThread extends Thread{
	private int start, finish; // intervalo de execucao da thread
	private int N; // dimensao das matrizes
	private int[][] grid, newgrid; // matrizes para operacao da thread

	/* Recebe um grid e um newgrid NxN.
	 * Recebe um intervalo de execucao [start, finish].
	 * Salva os atributos da thread. */
	public lifeThread(int[][] grid, int[][] newgrid, int start, int finish, int N){
		this.grid = grid;
		this.newgrid = newgrid;
		this.start = start;
		this.finish = finish;
		this.N = N;
	}

	/* Recebe um grid NxN.
 	 * Retorna o numero de vizinhos vivos de (i, j). */
	private int getNeighbors(int i, int j){
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

 	/* Simula uma geracao atualizando as celular de newgrid.
 	 * Executa dentro do intervalo [start, finish]. */
	public void run(){
		int i, j, neighbors;

		for(i = start; i <= finish; i++){
			for(j = 0; j < N; j++){
				neighbors = getNeighbors(i, j);

				// 1) Celula viva com 2 ou 3 vizinhos deve sobreviver
				if(grid[i][j] == 1 && (neighbors == 2 || neighbors == 3))
					newgrid[i][j] = 1;

				// 2) Celula morta com 3 vizinhos torna-se viva
				else if(grid[i][j] == 0 && neighbors == 3)
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


}