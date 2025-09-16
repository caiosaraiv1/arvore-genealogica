/*
 * Classe Main: ponto de entrada da aplicação.
 * Será responsável por criar a árvore genealógica,
 * ler dados de entrada e realizar consultas de parentesco
 * entre as pessoas da árvore.
 */ 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
	
	public static void main(String[] args) {
		ArvoreGenealogica arvore = new ArvoreGenealogica();

 		File arquivo = new File("arvore-genealogica\\arquivo.txt");

    	// try: Fecha o Scanner automaticamente
    	try (Scanner S = new Scanner(arquivo)) {
      		while (S.hasNextLine()) {
        	String dado = S.nextLine();
			String[] filhoPai = dado.split("\\s+"); //esse split separa quando tem espaços de quantidade indefinidas (\\s+)
			String filho = filhoPai[0];
			String pai = filhoPai[1];
        	System.out.println(dado);
			System.out.println(filho);
			System.out.println(pai);
      		}
    	} catch (FileNotFoundException e) {
      		System.out.println("Um erro ocorreu ao ler o arquivo.");
      		e.printStackTrace();
    	}
	}

}
