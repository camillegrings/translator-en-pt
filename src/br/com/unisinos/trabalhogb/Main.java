package br.com.unisinos.trabalhogb;

import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		Tradutor tradutor = new Tradutor();
		
		try {	
			tradutor.carregaDicionario("dicionario.dat");
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		System.out.println("Bem Vindo ao Tradutor (en-pt) \n");
		
		boolean stop = false;
		
		do {
			
			System.out.println("Informe a palavra que deseja traduzir: ");
			Scanner reader = new Scanner(System.in);
			String palavra = reader.nextLine();
			List traducoes = tradutor.traduzPalavra( palavra );
			
			if(traducoes == null) {
				
				System.out.println("A palavra "+ palavra + " n�o foi localizada. \n"
						         + "Deseja traduz�-la? (s/n) \n");
				String opt = reader.nextLine();
				
				while ( opt.compareTo("s")  != 0 && opt.compareTo("n")  != 0 ) {
					
					System.out.println("Op��o Inv�lida, digite 's' para Sim ou 'n' para N�o. \n");
					opt = reader.nextLine();	
					
				}
				
				if ( opt.compareTo("s") == 0) {
					
					boolean sair = false;
					traducoes = new List();
					
					while( !sair ) {
					    
						System.out.println("Digite a tradu��o: \n");
						traducoes.insertAtBack( reader.nextLine() );
						System.out.println("Inserir mais uma tradu��o? ('s' para Sim, qualquer outra op��o para N�o) \n");
						
						if( opt.compareTo(reader.nextLine())  != 0 ) {
							
							sair = true;
							
					    }
						
					}
					
					tradutor.insereTraducao(palavra, traducoes);
					System.out.println("Deseja gravar as altera��es? (s/n)"); 
					opt = reader.nextLine();
					
					while ( opt.compareTo("s")  != 0 && opt.compareTo("n")  != 0 ) {
						
						System.out.println("Op��o Inv�lida, digite 's' para Sim ou 'n' para N�o. \n");
						opt = reader.nextLine();	
						
					}
					
					if(opt.compareTo("s")  == 0) {
						
						try {
							tradutor.salvaDicionario("dicionario.dat");
							System.out.println("Atualizado com sucesso.");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}else {
						
						System.out.println("Atualiza��o cancelada pelo usu�rio.");
						//Teremos que implementar um delete na avl para essa a��o
						
					}

				}
				
			}else {
				
				traducoes.print();
				
			}
			
			System.out.println("Deseja realizar mais alguma tradu��o? ('s' para Sim, qualquer outra op��o para N�o)  \n");
			
			if(reader.nextLine().compareTo("s") != 0) {
				
				stop = true;
				reader.close();
				
			}
		}while ( !stop );
		
		System.out.println("Programa Finalizado. \n");
		
	}
}
