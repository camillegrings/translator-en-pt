package br.com.unisinos.trabalhogb;

import java.io.BufferedWriter;
import java.io.IOException;

public class AvlTree {

	private AvlNode root = null;

	public AvlTree() {
		root = null;
	}

//	public void clear() {
//		root = null;
//	}
//	public boolean isEmpty() {
//		return root == null;
//	}
//
//	public AvlNode getRootNode (){
//		return root;
//	}

	private static int height( AvlNode node ) {
		if (node == null)
			return -1; //Se o nodo é null, retorna -1
		else
			return node.height;
	}

	private static int getMaior( int leftNode, int rightNode ) {
		if (leftNode > rightNode)
			return leftNode;
		else
			return rightNode;
	}

	private int getFactor (AvlNode node) {
		//Se node for null, left retorna -1, right retorna -1, na expressão (-1)-(-1) = 0
		//Se algum dos nodes estiver preenchido, usa o seu atributo de altura
		return height( node.left ) - height( node.right );
	}
	
	public boolean insert (Dicionario d) {
		root = insert (d, root);
		return true;
	}

	private AvlNode insert (Dicionario d, AvlNode node) {
		//Se o node for null, instancia um node
		if( node == null ) {
			node = new AvlNode( d, null,null);
		}
		//Compara a key do objeto a ser inserido com a key já existente na árvore:
		//Se a chave do objeto for > 0, significa que a chave do objeto a ser inserido é menor que a chave existente,
		//portanto, insere a esquerda
		else if( d.compareTo(node.Key) > 0 ) { 
			node.left = insert( d, node.left );
		}
		//Se a chave do objeto for < 0, a chave do objeto a ser inserido é maior que a chave existente, logo insere a direita
		else if( d.compareTo(node.Key) < 0) {
			node.right = insert( d, node.right );
		}
        //Após realizada a operação de inserção, verifica o fator do node para determinar se a árvore está balanceada
		if ( getFactor(node) == 2 ) { //Se o fator for 2, a árvore não está balanceada
			if (getFactor (node.left)>0) //Sendo o fator da sub-arvore à esquerda positivo, faz rotação simples à direita
				node = rotateSimpleToRight( node );
			else // Senão, faz rotação dupla à direita
				node = rotateDoubleToRight( node );
		}
		else if ( getFactor(node) == -2 ) {//Se o fator for -2, a árvore não está balanceada
			if ( getFactor(node.right)<0 ) //Sendo o fator da sub-arvore à direita negativo, faz rotação simples à direita
				node = rotateSimpleToLeft( node );
			else  // Senão, faz rotação dupla à esquerda
				node = rotateDoubleToLeft( node );
		}
        
		//Determina a altura do nodo, considerando a maior das alturas entre left e rigth + 1
		/*
		                |4| (2+1) ->3, maior altura entre a sub arvore da esquerda e direita + 1
		                 |
		       |2| (1+1)       |5| (0+1)
		        |
		     |1| |3| (0+1)     
		 */
		node.height = getMaior( height( node.left ), height( node.right ) ) + 1;  
		return node;
	}

	private static AvlNode rotateSimpleToRight( AvlNode k2 ) {
		AvlNode k1 = k2.left;//Filho esquerdo de K2 vira pai 
		k2.left = k1.right;  //Filho direito de k1 vira filho esquerdo de k2
		k1.right = k2;       //k2 vira filho direito de k1
		k2.height = getMaior( height( k2.left ), height( k2.right ) ) + 1; // A altura de k2 é definida pela maior altura entre suas sub arvores direita e esquerda, + 1
		k1.height = getMaior( height( k1.left ), k2.height ) + 1; //A altura de k1 é determinada pela maior altura entre a altura de k2 e a altura da sub arvore esquerda de k1
		return k1; //Retorna o node rotacionado
	}

	private static AvlNode rotateSimpleToLeft( AvlNode k1 ) {
		AvlNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = getMaior( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = getMaior( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	private static AvlNode rotateDoubleToRight( AvlNode k3 ) {
		k3.left = rotateSimpleToLeft( k3.left );
		return rotateSimpleToRight( k3 );
	}

	private static AvlNode rotateDoubleToLeft( AvlNode k1 ) {
		k1.right = rotateSimpleToRight( k1.right );
		return rotateSimpleToLeft( k1 );
	}

	/*public AvlNode search(String palavra) {
		return search(root,palavra);
	}*/
	
	/*protected AvlNode search(AvlNode p, String palavra) {
		while (p != null) {
			if (palavra.compareTo(p.Key.getPalavra()) == 0) return p;
			else if (palavra.compareTo(p.Key.getPalavra()) < 0) p = p.left;
			else p = p.right;
		}
		return null;
	}*/

//	public void inorder() {
//		inorder(root);
//	}
//	protected void inorder(AvlNode p) {
//		if (p != null) {
//			inorder(p.left);
//			System.out.print(p.Key.palavra + " - Traduções:");
//			p.Key.printDefinicoes(); 
//			System.out.print("\n");
//			inorder(p.right);
//		}
//	}

	public void saveInPreOrder(BufferedWriter outputStream) throws IOException {
		preOrder(root, outputStream);
	}

	protected void preOrder(AvlNode node, BufferedWriter outputStream) throws IOException {
		String line;
		if (node != null) {
			line = node.Key.palavra + "#" + node.Key.getDefinicoesAsString();
			outputStream.write(line);
			outputStream.newLine();
			preOrder(node.left, outputStream);
			preOrder(node.right, outputStream);
		}
	}

//	public void postorder() {
//		postorder(root);
//	}
//
//	protected void postorder(AvlNode p) {
//		if (p != null) {
//			postorder(p.left);
//			postorder(p.right);
//			
//			System.out.print(p.Key.palavra + " - Traduções:");
//			p.Key.printDefinicoes(); 
//			System.out.print("\n");
//		}
//	}    
}