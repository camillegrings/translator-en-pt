package br.com.unisinos.trabalhogb;

import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		Tradutor tradutor = new Tradutor();
		try {
			tradutor.carregaDicionario("dicionario.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			tradutor.salvaDicionario("dicionario2.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
