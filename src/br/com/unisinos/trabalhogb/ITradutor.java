package br.com.unisinos.trabalhogb;

import java.io.IOException;

public interface ITradutor {
	public void traduzPalavra(String palavra);
	public void insereTraducao(String palavra, List definicoes);
	public void salvaDicionario(String arq) throws IOException;
}
