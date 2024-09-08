package controller;

import java.util.concurrent.Semaphore;

public class ThreadServidor extends Thread {

	int id;
	Semaphore semaforo;
	
	public ThreadServidor(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		int id = this.id;
		double max;
		double min;
		
		if (id % 3 == 1) {
			min = 0.2;
			max = 1.0;
			
			calculo(tempoRandom(min, max));
			transacaoDB(1.0);
			calculo(tempoRandom(min, max));
			transacaoDB(1.0);
		} else if (id % 3 == 2) {
			min = 0.5;
			max = 1.5;
			
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
		} else {
			min = 1.0;
			max = 2.0;
			
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
			calculo(tempoRandom(min, max));
			transacaoDB(1.5);
		}
	}
	
	private void calculo(double tempo) {
		System.out.println(String.format("#%02d Fazendo cálculos: %.2f segundos", this.id, tempo));
	}
	
	private void transacaoDB(double tempo) {
		// Início da sessão crítica
		try {
			semaforo.acquire();
			System.out.println(String.format("#%02d Fazendo transação de BD: %.2f segundos", this.id, tempo));
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
			// Fim da sessão crítica
		}
	}
	
	private double tempoRandom(double min, double max) {
		return Math.random() * (max - min) + min;
	}
	
}
