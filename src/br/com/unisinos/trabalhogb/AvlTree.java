package br.com.unisinos.trabalhogb;

import java.io.BufferedWriter;
import java.io.IOException;

public class AvlTree {

	private AvlNode root = null;
    
	//Construtor da Avl Tree
	public AvlTree() {
		root = null;
	}

	private static int height(AvlNode node) {
		if (node == null)
			return -1; // Se o nodo � null, retorna -1
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
		// Se node for null, left retorna -1, right retorna -1, na express�o (-1)-(-1) =
		// 0
		// Se algum dos nodes estiver preenchido, usa o seu atributo de altura
		return height(node.getLeft()) - height(node.getRight());
	}

	public void insert(Dicionario dicionario) {
		root = insert(dicionario, root);
	}

	private AvlNode insert(Dicionario d, AvlNode node) {
		// Se o node for null, instancia um node
		if (node == null) {
			node = new AvlNode(d, null, null);
		}
		// Compara a key do objeto a ser inserido com a key j� existente na �rvore:
		// Se a chave do objeto for > 0, significa que a chave do objeto a ser inserido
		// � menor que a chave existente,
		// portanto, insere a esquerda
		else if (d.compareTo(node.getKey()) > 0) {
			node.setLeft((insert(d, node.getLeft())));
		}
		// Se a chave do objeto for < 0, a chave do objeto a ser inserido � maior que a
		// chave existente, logo insere a direita
		else if (d.compareTo(node.getKey()) < 0) {
			node.setRight(insert(d, node.getRight()));
		}
		// Ap�s realizada a opera��o de inser��o, verifica o fator do node para
		// determinar se a �rvore est� balanceada
		if (getFactor(node) == 2) { // Se o fator for 2, a �rvore n�o est� balanceada
			if (getFactor(node.getLeft()) > 0) // Sendo o fator da sub-arvore � esquerda positivo, faz rota��o simples �
												// direita
				node = rotateSimpleToRight(node);
			else // Sen�o, faz rota��o dupla � direita
				node = rotateDoubleToRight(node);
		} else if (getFactor(node) == -2) {// Se o fator for -2, a �rvore n�o est� balanceada
			if (getFactor(node.getRight()) < 0) // Sendo o fator da sub-arvore � direita negativo, faz rota��o simples �
												// direita
				node = rotateSimpleToLeft(node);
			else // Sen�o, faz rota��o dupla � esquerda
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
		AvlNode k1 = k2.getLeft();// Filho esquerdo de K2 vira pai
		k2.setLeft(k1.getRight()); // Filho direito de k1 vira filho esquerdo de k2
		k1.setRight(k2); // k2 vira filho direito de k1
		k2.setHeight(getBiggerNode(height(k2.getLeft()), height(k2.getRight())) + 1); // A altura de k2 � definida pela maior
																					// altura entre suas sub arvores
																					// direita e esquerda, + 1
		k1.setHeight(getBiggerNode(height(k1.getLeft()), k2.getHeight()) + 1); // A altura de k1 � determinada pela maior
																			// altura entre a altura de k2 e a altura da
																			// sub arvore esquerda de k1
		return k1; // Retorna o node rotacionado
	}

	private static AvlNode rotateSimpleToLeft(AvlNode k1) {
		AvlNode k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(getBiggerNode(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(getBiggerNode(height(k2.getRight()), k1.getHeight()) + 1);
		return k2;
	}

	private static AvlNode rotateDoubleToRight(AvlNode k3) {
		k3.setLeft(rotateSimpleToLeft(k3.getLeft()));
		return rotateSimpleToRight(k3);
	}

	private static AvlNode rotateDoubleToLeft(AvlNode k1) {
		k1.setRight(rotateSimpleToRight(k1.getRight()));
		return rotateSimpleToLeft(k1);
	}

	public AvlNode buscaPalavra(String palavra) {
		// Inicia pela ra�z a pesquisa pela palavra
		return buscaPalavra(root, palavra);
	}

	protected AvlNode buscaPalavra(AvlNode node, String palavra) {
		/*
		 * Percorre a �rvore para a direita ou esquerda (depende da palavra pesquisada).
		 * Assim que achar a palavra retorna o node
		 * 
		 * */
		if ( node == null ) {
			return null;

		if (palavra.compareTo(node.getKey().getPalavra()) == 0) {
			return node;
		}
		
		if (palavra.compareTo(node.getKey().getPalavra()) < 0) {
			node = buscaPalavra(node.getLeft(), palavra);
			return node;
		}
		
		node = buscaPalavra(node.getRight(), palavra);
		return node;
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