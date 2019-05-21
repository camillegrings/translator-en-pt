package br.com.unisinos.trabalhogb;

import java.io.BufferedWriter;
import java.io.IOException;

public class AvlTree {

	private AvlNode root;
    
	//Construtor da Avl Tree
	public AvlTree() {
		root = null;
	}

	private static int height(AvlNode node) {
		if (node == null)
			return -1; // Se o nodo ï¿½ null, retorna -1
		else
			return node.getHeight();
	}

	//Compara as alturas de dois nodes e retorna a maior altura entre eles
	private static int getBiggerNode(int leftNode, int rightNode) {
		if (leftNode > rightNode)
			return leftNode;
		else
			return rightNode;
	}
	
	//Calcula o fator do node visitado
	private int getFactor(AvlNode node) {
		// Se node for null, left retorna -1, right retorna -1, na expressï¿½o (-1)-(-1) =
		// 0
		// Se algum dos nodes estiver preenchido, usa o seu atributo de altura
		return height(node.getLeft()) - height(node.getRight());
	}
	
	//Acesso para a inserção
	public void insert(Dicionario dicionario) {
		root = insert(dicionario, root);
	}
	
	//Inserção 
	private AvlNode insert(Dicionario d, AvlNode node) {
		/*
		 Percorre a árvore (esquerda ou direita) até encontrar uma posição livre.
		 Assim que localizado, insere o node, verifica o fator, determina a altura e retorna o node criado
		*/
		// Se o node for null, instancia um node
		if (node == null) {
			node = new AvlNode(d, null, null);
		}
		// Compara a key do objeto a ser inserido com a key jï¿½ existente na ï¿½rvore:
		// Se a chave do objeto for > 0, significa que a chave do objeto a ser inserido
		// ï¿½ menor que a chave existente,
		// portanto, insere a esquerda
		else if (d.compareTo(node.getKey()) > 0) {
			node.setLeft((insert(d, node.getLeft())));
		}
		// Se a chave do objeto for < 0, a chave do objeto a ser inserido ï¿½ maior que a
		// chave existente, logo insere a direita
		else if (d.compareTo(node.getKey()) < 0) {
			node.setRight(insert(d, node.getRight()));
		}
		//Conforme o método realiza regressão até a base da pilha, vai verificando se algum fator estourou para fazer a devida rotação
		if (getFactor(node) == 2) { // Se o fator for 2, a árvore não está balanceada
			if (getFactor(node.getLeft()) > 0) 
				// Sendo o fator da sub-arvore à esquerda positivo, faz rotação simples à direita
				node = rotateSimpleToRight(node);
			else 
				// Senão, faz rotação dupla à direita
				node = rotateDoubleToRight(node);
		} else if (getFactor(node) == -2) {// Se o fator for -2, a árvore não está balanceada
			if (getFactor(node.getRight()) < 0) 
				//Sendo o fator da sub-arvore à direita negativo, faz rotação simples à direita
				node = rotateSimpleToLeft(node);
			else 
				// Senão, faz rotação dupla à esquerda
				node = rotateDoubleToLeft(node);
		}

		// Determina a altura do nodo, considerando a maior das alturas entre left e
		// rigth + 1
		/*
		 * |4| (2+1) ->3, maior altura entre a sub arvore da esquerda e direita + 1 |
		 * |2| (1+1) |5| (0+1) | |1| |3| (0+1)
		 */
		node.setHeight(getBiggerNode(height(node.getLeft()), height(node.getRight())) + 1);
		return node;
	}

	private static AvlNode rotateSimpleToRight(AvlNode k2) {
		//Rotação Simples para a direita
		AvlNode k1 = k2.getLeft();// Filho esquerdo de K2 vira pai
		k2.setLeft(k1.getRight()); // Filho direito de k1 vira filho esquerdo de k2
		k1.setRight(k2); // k2 vira filho direito de k1
		
		//Calcula a altura dos nodes k1 e k2
		k2.setHeight(getBiggerNode(height(k2.getLeft()), height(k2.getRight())) + 1); // A altura de k2 ï¿½ definida pela maior
																					// altura entre suas sub arvores
																					// direita e esquerda, + 1
		k1.setHeight(getBiggerNode(height(k1.getLeft()), k2.getHeight()) + 1); // A altura de k1 ï¿½ determinada pela maior
																			// altura entre a altura de k2 e a altura da
																			// sub arvore esquerda de k1
		return k1; // Retorna o node rotacionado
	}

	private static AvlNode rotateSimpleToLeft(AvlNode k1) {
		//Rotação Simples para a esquerda
		AvlNode k2 = k1.getRight(); //Filho direito de k1 vira pai
		k1.setRight(k2.getLeft());//Filho esquerdo de k2 vira o filho direito de k1
		k2.setLeft(k1); //k1 vira filho esquerdo de k2
		
		//Calcula a altura dos nodes k2 e k1
		k1.setHeight(getBiggerNode(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(getBiggerNode(height(k2.getRight()), k1.getHeight()) + 1);
		return k2; //Retorna o node rotacionado
	}

	private static AvlNode rotateDoubleToRight(AvlNode k3) {
		/*    Estouro         Rot.Esquerda(k1)   Rot.direita(k3)
		 *       |k3|              |k3|             |k2|
		 *    |k1|      -->     |k2|     -->     |k1|  |k3|
		 *       |k2|        |k1|
		 */		
		k3.setLeft(rotateSimpleToLeft(k3.getLeft())); //Rotação simples à esquerda em k1(filho esquerdo de k3)
		return rotateSimpleToRight(k3); //retorna rotação simples à direita em k3
	}

	private static AvlNode rotateDoubleToLeft(AvlNode k1) {
		/*    Estouro         Rot.Esquerda(k3)       Rot.direita(k2)
		 *       |k1|               |k1|                   |k2|
		 *          |k3| -->           |k2|     -->     |k1|  |k3|
		 *       |k2|                     |k3|
		 */
		k1.setRight(rotateSimpleToRight(k1.getRight())); //Rotação simples à direita em k3(filho direito de k1)
		return rotateSimpleToLeft(k1); //retorna rotação simples à esquerda em k1
	}

	public AvlNode buscaPalavra(String palavra) {
		// Inicia pela raiz a pesquisa pela palavra
		return buscaPalavra(root, palavra);
	}

	protected AvlNode buscaPalavra(AvlNode node, String palavra) {
		/*
		 * Percorre a arvore para a direita ou esquerda (depende da palavra pesquisada).
		 * Assim que achar a palavra retorna o node
		 * 
		 * */
		if ( node == null )
			return null;

		if (palavra.compareTo(node.getKey().getPalavra()) == 0) {
			return node;
		}
		
		if (palavra.compareTo(node.getKey().getPalavra()) < 0) {
			return buscaPalavra ( node.getLeft(), palavra );
		}
		
		return buscaPalavra ( node.getRight(), palavra );
	}

	public void saveInPreOrder(BufferedWriter outputStream) throws IOException {
		saveInPreOrder(root, outputStream);
	}

	protected void saveInPreOrder(AvlNode node, BufferedWriter outputStream) throws IOException {
		String line;
		if (node != null) {
			line = node.getKey().getPalavra() + "#" + node.getKey().getDefinicoesAsString();
			outputStream.write(line);
			outputStream.newLine();
			saveInPreOrder(node.getLeft(), outputStream);
			saveInPreOrder(node.getRight(), outputStream);
		}
	}
}