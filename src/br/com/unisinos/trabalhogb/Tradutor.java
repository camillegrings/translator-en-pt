package br.com.unisinos.trabalhogb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tradutor implements ITradutor {
	private AvlTree avl;

	public Tradutor() {
		this.avl = new AvlTree();
	}

	protected void carregaDicionario(String arq) throws IOException {
		// Carregar Arquivo
		try (BufferedReader inputStream = new BufferedReader(new FileReader(new File(arq)))) {
			String line;

			// Separa a linha em palavra em Ingl�s e suas tradu��es
			while ((line = inputStream.readLine()) != null) {
				String[] palavras = line.split("#");
				String palavraIngles = palavras[0];
				List definicoes = new List("Defini��es");
				for (int i = 1; i < palavras.length; i++) {
					definicoes.insertAtBack(palavras[i]);
				}

				Dicionario dicionario = new Dicionario(palavraIngles, definicoes);
				avl.insert(dicionario);
			}
		}
	}


	@Override
	public List traduzPalavra(String palavra) {
		// Busca a palavra, se existir retorna a lista das defini��es, se n�o retorna nulo
		AvlNode node = avl.buscaPalavra(palavra);
	    return node == null ? null : node.getKey().getDefinicoes();
	}	

	@Override
	public void insereTraducao(String palavra, List definicoes) {
		// Procura se a palavra (chave) j� existe
		// Se n�o existe, o dicionario � criado e inserido na avl
		// Se j� existe, n�o ser� poss�vel inserir pois uma avl n�o aceita chaves duplicadas
		AvlNode node = avl.buscaPalavra(palavra);
		
		if (node == null) {
			criaNovoDicionario(palavra, definicoes);
			System.out.println("Palavra " + palavra + " inclu�da com sucesso.");
		} else {
			System.out.println("Essa palavra j� existe.");
		}
	}

	@Override
	public void salvaDicionario(String arq) throws IOException {
		// Salva a avl no arquivo
		try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(new File(arq)))) {
			avl.saveInPreOrder(outputStream);
		}
	}
	
	private void criaNovoDicionario(String palavra, List definicoes) {
		// Cria o dicionario com palavra e definicoes, e insere na avl
		Dicionario novoDicionario = new Dicionario(palavra, definicoes);
		avl.insert(novoDicionario);
	}
	
	public AvlTree getTree() {
		return avl;
	}
}
