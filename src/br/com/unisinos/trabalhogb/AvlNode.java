package br.com.unisinos.trabalhogb;

public class AvlNode {

	private int height;
	private Dicionario Key;
	private AvlNode left, right;

	public AvlNode(Dicionario dicionario) {
		this(dicionario, null, null);
	}

	public AvlNode(Dicionario dicionario, AvlNode left, AvlNode right) {
		this.Key = dicionario;
		this.left = left;
		this.right = right;
		this.height = 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Dicionario getKey() {
		return Key;
	}

	public void setKey(Dicionario key) {
		Key = key;
	}

	public AvlNode getLeft() {
		return left;
	}

	public void setLeft(AvlNode left) {
		this.left = left;
	}

	public AvlNode getRight() {
		return right;
	}

	public void setRight(AvlNode right) {
		this.right = right;
	}

}
