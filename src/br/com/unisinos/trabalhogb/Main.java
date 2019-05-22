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
		
		tradutor.traduzPalavra("rambling");
		tradutor.traduzPalavra("hello");
		
		List definicoesCar = new List();
		definicoesCar.insertAtBack("carro");
		tradutor.insereTraducao("car", definicoesCar);
		
		List definicoesRambling = new List();
		definicoesRambling.insertAtBack("divagar");
		tradutor.insereTraducao("rambling", definicoesRambling);
		
		try {
			tradutor.salvaDicionario("dicionario2.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
