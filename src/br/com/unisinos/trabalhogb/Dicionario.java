package br.com.unisinos.trabalhogb;


public class Dicionario {
	private String palavra;
	private List definicoes;
	
	public Dicionario(String palavra, List definicoes) {
		this.palavra = palavra;
		this.definicoes = definicoes;
	}

	public int compareTo(Object o) {
		//Compara palavra (chave da avl)
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
		// Printa as Defini��es com Syso
		definicoes.print();
	}
	
	public String getDefinicoesAsString() {
		//Concatena as defini��es em uma String, separando as defini��es com #
		return definicoes.getAsString();
	}

}
