/* (22/05/2019) Jeferson Michelin:
 * Removi os inputs de teclado e joguei do Tradutor e joguei no Main
 * O método traduzPalavra, precisava retornar um List, então alterei ele
 * Testei os demais casos lá no main e estão funcionando, podemos dar uma melhorada no código depois pq ficou bem feio haha
 * Tbm vamos precisar fazer um delete lá avl :(
 * */

package br.com.unisinos.trabalhogb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tradutor implements ITradutor {
	
	protected AvlTree avl;

	public Tradutor() {
		this.avl = new AvlTree();
	}

	protected void carregaDicionario(String arq) throws IOException {
		// Carregar Arquivo
		try (BufferedReader inputStream = new BufferedReader(new FileReader(new File(arq)))) {
			String line;

			// Separa a linha em palavra em Inglês e suas traduções
			while ((line = inputStream.readLine()) != null) {
				String[] palavras = line.split("#");
				String palavraIngles = palavras[0];
				List definicoes = new List("Definições");
				for (int i = 1; i < palavras.length; i++) {
					definicoes.insertAtBack(palavras[i]);
				}

				Dicionario dicionario = new Dicionario(palavraIngles, definicoes);
				avl.insert(dicionario);
			}
		}
	}


/*	public void traduzPalavra(String palavra) {
		AvlNode node = avl.buscaPalavra(palavra);
		if (node == null) {
			System.out.println("Palavra " + palavra +  " não encontrada");

			Scanner reader = new Scanner(System.in);
			System.out.println("Deseja inserir essa palavra? (y/n)");
			String output = reader.next();
			if (output.equals("y")) {
				List novasDefinicoes = insereDefinicoes(palavra);
				criaNovoDicionario(palavra, novasDefinicoes);
				System.out.println("Palavra incluída com sucesso.");
			}
			reader.close();
		} else {
			node.getKey().getDefinicoes().print();
		}
	}*/
	@Override
	public List traduzPalavra(String palavra) {
		
		AvlNode node = avl.buscaPalavra(palavra);
	    if ( node == null ) {
	    	return null;
	    }else {
	    	return node.getKey().getDefinicoes();	
	    }
		
	}	

	@Override
	public void insereTraducao(String palavra, List definicoes) {
		
		AvlNode node = avl.buscaPalavra(palavra);
		
		if (node == null) {
		
			criaNovoDicionario(palavra, definicoes);
			System.out.println("Palavra " + palavra + " incluída com sucesso.");
		
		} else {
			//Scanner reader = new Scanner(System.in);
			System.out.println("Essa palavra já existe.");
			//System.out.println("Deseja incluir essas definições à palavra? (y/n)");
			//String output = reader.next();
			//reader.close();
			//if (output.equals("y")) {
			//	node.getKey().getDefinicoes().insertAtBack(definicoes);
			//}
		}
	}

	@Override
	public void salvaDicionario(String arq) throws IOException {
		try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File(arq)))) {
			avl.saveInPreOrder(outputStream);
		}
	}
	
	private void criaNovoDicionario(String palavra, List definicoes) {
		Dicionario novoDicionario = new Dicionario(palavra, definicoes);
		avl.insert(novoDicionario);
	}
	
	public AvlTree getTree() {
		return avl;
	}
	/*private List insereDefinicoes(String palavra) {
		String output;
		List novaLista = new List();
		Scanner reader = new Scanner(System.in);
		do {
			System.out.println("Digite a definição da palavra " + palavra + ":");
			String definicao = reader.next();
			novaLista.insertAtBack(definicao);
			System.out.println("Deseja inserir mais uma definição? (y/n)");
			output = reader.next();
		} while (!output.equals("n"));
		reader.close();
		return novaLista;
	}*/
}
