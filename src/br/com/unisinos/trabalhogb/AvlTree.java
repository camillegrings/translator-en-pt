package br.com.unisinos.trabalhogb;

public class AvlTree {

	private AvlNode root = null;

	public AvlTree(  ) {
		root = null;
	}

	public void clear() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}

	public AvlNode getRootNode (){
		return root;
	}

	private static int height( AvlNode t ) {
		if (t == null)
			return -1;
		else
			return t.height;
	}

	private static int max( int lhs, int rhs ) {
		if (lhs > rhs)
			return lhs;
		else
			return rhs;
	}

	private int getFactor (AvlNode t) {
		return height( t.left ) - height( t.right );
	}

	public boolean insert (Dicionario d) {
		root = insert (d, root);
		return true;
	}

	private AvlNode insert (Dicionario d, AvlNode t) {
		if( t == null ) {
			t = new AvlNode( d, null,null);
		}
		else if( d.compareTo(t.Key) > 0 ) { 
			t.left = insert( d, t.left );
		}
		else if( d.compareTo(t.Key) < 0) {
			t.right = insert( d, t.right );
		}

		if ( getFactor(t) == 2 ) {
			if (getFactor (t.left)>0) t = doRightRotation( t );
			else t = doDoubleRightRotation( t );
		}
		else if ( getFactor(t) == -2 ) {
			if ( getFactor(t.right)<0 ) t = doLeftRotation( t );
			else t = doDoubleLeftRotation( t );
		}

		t.height = max( height( t.left ), height( t.right ) ) + 1;  
		return t;
	}

	private static AvlNode doRightRotation( AvlNode k2 ) {
		AvlNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = max( height( k1.left ), k2.height ) + 1;
		return k1;
	}

	private static AvlNode doLeftRotation( AvlNode k1 ) {
		AvlNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	private static AvlNode doDoubleRightRotation( AvlNode k3 ) {
		k3.left = doLeftRotation( k3.left );
		return doRightRotation( k3 );
	}

	private static AvlNode doDoubleLeftRotation( AvlNode k1 ) {
		k1.right = doRightRotation( k1.right );
		return doLeftRotation( k1 );
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

	public void inorder() {
		inorder(root);
	}
	protected void inorder(AvlNode p) {
		if (p != null) {
			inorder(p.left);
			System.out.print(p.Key+" - ");
			inorder(p.right);
		}
	}

	public void preorder() {
		preorder(root);
	}
	protected void preorder(AvlNode p) {
		if (p != null) {
			System.out.print(p.Key + " ");
			preorder(p.left);
			preorder(p.right);
		}
	}

	public void postorder() {
		postorder(root);
	}

	protected void postorder(AvlNode p) {
		if (p != null) {
			postorder(p.left);
			postorder(p.right);
			System.out.print(p.Key + " ");
		}
	}    
}