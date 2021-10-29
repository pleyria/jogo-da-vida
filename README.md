# Jogo da vida - Atividade de Programação Concorrente e Distribuída

## Descrição
Implementação de versões concorrentes e seriais do jogo da vida em linguagem C utilizando OpenMP e em linguagem Java utilizando *threads* java. 

## Estrutura dos Arquivos

* Concorrente
	* highLife_C: *HighLife* em linguagem C com *threads* (OpenMP).
	* highLife_Java: *HighLife* em linguagem Java com *threads* (JavaThreads).
	* Life_C: *Game of Life* em linguagem C com *threads* (OpenMP).
	* Life_Java: *Game of Life* em linguagem Java com *threads* (JavaThreads).
* Serial
	* highLife_C: *HighLife* em linguagem C sem *threads*.
	* highLife_Java: *HighLife* em linguagem Java sem *threads*.
	* Life_C: *Game of Life* em linguagem C sem *threads*.
	* Life_Java: *Game of Life* em linguagem Java sem *threads*.

## Especificações da Máquina Usada
Testes feitos no processador AMD Ryzen 5 2400G (4 núcleos reais e 8 threads com *hyperthreading*), com 16 GB de memória RAM e usando o sistema operacional Linux Mint 20.2 Cinnamon.

Código em linguagem C compilado com o compilador GCC versão 9.3.0 e a biblioteca OpenMP versão 4.5.

Código em linguagem Java compilado com o compilador javac versão 17.

## Resultados

Resultados de testes de desempenho dos algoritmos disponíveis [nesta tabela](https://docs.google.com/spreadsheets/d/1Qy7jPkMfUVpZgRr0P_HdWd3AHZ-u1UlbYcqM43EOKeI/edit?usp=sharing).

Parâmetros usados:

* Dimensão do tabuleiro: 2048 x 2048
* Número de gerações: 2000
* Número de threads: 1, 2, 4 e 8.
