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

 		File arquivo = new File("arquivo.txt");

    	// try: Fecha o Scanner automaticamente
    	try (Scanner S = new Scanner(arquivo)) {
      		while (S.hasNextLine()) {
        		String dado = S.nextLine();
				String[] filhoPai = dado.split("\\s+"); 
				
				String filho = filhoPai[0];
				String pai = filhoPai[1];

				// Adiciona na árvore (pai, filho)
				arvore.criarNo(pai, filho);

				System.out.println("Inserido: pai=" + pai + ", filho=" + filho);
      		}
    	} catch (FileNotFoundException e) {
      		System.out.println("Um erro ocorreu ao ler o arquivo.");
      		e.printStackTrace();
    	}

		// Exemplo: buscar alguém
		System.out.println("\nTeste de busca:");
		System.out.println("Buscando 'carlos.souza': " + arvore.buscarPessoa("carlos.souza"));
		System.out.println("Buscando 'patricia.fischer': " + arvore.buscarPessoa("patricia.fischer"));
		Pessoa Dennis = arvore.buscarPessoa("dennis.ritchie");
		Pessoa Les = arvore.buscarPessoa("les.valiant");
		Pessoa Heitor = arvore.buscarPessoa("heitor.maciel");
		Pessoa Oswaldo = arvore.buscarPessoa("oswaldo.veblen");
		Pessoa Carlos = arvore.buscarPessoa("carlos.souza");
		System.out.println("Achando ancestral comum entre Dennis R. e Les v.: " + arvore.ancestralComum(Dennis, Les));
		System.out.println("Nivel Dennis: " + arvore.nivel(Dennis));
		System.out.println("Nivel Les: " + arvore.nivel(Les));
		System.out.println("Distância entre Les e Heitor: " + arvore.distancia(Heitor, Oswaldo));
		System.out.println("Distância entre Dennis e Heitor: " + arvore.distancia(Carlos, Oswaldo));
		System.out.println("Parentesco Heitor e Carlos: " + arvore.Parentesco(Heitor, Carlos));


	}
}
