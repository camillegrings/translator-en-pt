package br.com.unisinos.trabalhogb;

import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		Tradutor tradutor = new Tradutor();
		try {
			tradutor.carregaDicionario("dicionario.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tradutor.salvaDicionario("teste");

	}

}
