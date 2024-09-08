package view;

import java.util.concurrent.Semaphore;

import controller.ThreadServidor;

public class Main {
	
	public static void main(String[] args) {
		
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int i = 1; i <= 21; i++) {
			Thread threadServidor = new ThreadServidor(i, semaforo);
			threadServidor.start();
		}
	}

}
