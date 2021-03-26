package view;

import java.util.concurrent.Semaphore;

import controller.Banco;

public class BancoMain {

	public static Semaphore semaforoSaque, semaforoDeposito;
	static int op, codigo;
	static float saldo, valor;

	public static void main(String[] args) {

		semaforoSaque = new Semaphore(1);
		semaforoDeposito = new Semaphore(1);

		for (int codigo = 1; codigo <= 10; codigo++) {
			op = (int)(Math.random()*10);
			saldo = (float) ((Math.random() * 1001) + 1000);
			valor = (float) ((Math.random() * 500) + 600);
			Thread t = new Banco(codigo, op, saldo, valor, semaforoDeposito, semaforoSaque);
			t.start();

		}

	}

}
