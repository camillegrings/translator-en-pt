package br.com.unisinos.trabalhogb;

    public class AvlNode {
    	
    	protected int height;
    	protected Dicionario Key;
        protected AvlNode left, right;

        public AvlNode ( Dicionario d ) {
            this( d, null, null );
        }

        public AvlNode ( Dicionario d, AvlNode lt, AvlNode rt ) {
            Key  = d;
        	left = lt;
            right = rt;
            height   = 0;
        }    
    }
