package br.com.unisinos.trabalhogb;

public class Dicionario {
	protected String palavra;
	protected List definicoes;
	
	public Dicionario(String palavra, List definicoes) {
		this.palavra = palavra;
		this.definicoes = definicoes;
	}

	public int compareTo(Object o) {
		Dicionario dicionario = (Dicionario) o;
		return dicionario.getPalavra().compareTo(this.getPalavra());
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public List getDefinicoes() {
		return definicoes;
	}

	public void setDefinicoes(List definicoes) {
		this.definicoes = definicoes;
	}
	
	
	public void printDefinicoes() {
		definicoes.print();
	}
	
	
	
}
