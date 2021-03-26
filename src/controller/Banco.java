package controller;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {

	private int codigo, op;
	private float saldo, valor;
	private Semaphore semaforoSaque, semaforoDeposito;

	public Banco(int codigo, int op, float saldo, float valor, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.codigo = codigo;
		this.op = op;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforoSaque = semaforoSaque;
		this.semaforoDeposito = semaforoDeposito;

	}

	@Override
	public void run() {
		int opc = op % 2;
		switch (opc) {
		case 0:
			try {
				semaforoDeposito.acquire();
				System.out.println("Depositando em... #" + codigo);
				Deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoDeposito.release();
			}
			break;
		case 1:
			try {
				semaforoSaque.acquire();
				System.out.println("Sacando em... #" + codigo);
				Saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoSaque.release();
			}
			break;
		}

	}

	private void Saque() {
		int tempo = (int) ((Math.random() * 1000) + 1);
		try {
			Thread.sleep(tempo);
			saldo += valor;
			System.err.println("Novo saldo da conta #" + codigo + " pós saque: R$" + saldo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void Deposito() {
		int tempo = (int) ((Math.random() * 1000) + 1);
		try {
			Thread.sleep(tempo);
			saldo += valor;
			System.err.println("Novo saldo da conta #" + codigo + " pós depósito: R$" + saldo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
