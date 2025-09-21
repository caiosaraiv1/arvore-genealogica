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

        // --- 1. Carregando dados do arquivo ---
        System.out.println("--- 1. Carregando dados do arquivo ---");
        File arquivo = new File("arquivo.txt");

        try (Scanner S = new Scanner(arquivo)) {
            while (S.hasNextLine()) {
                String dado = S.nextLine();
                String[] filhoPai = dado.split("\\s+");

                if (filhoPai.length == 2) {
                    String filho = filhoPai[0];
                    String pai = filhoPai[1];
                    arvore.criarNo(pai, filho);
                    System.out.println("Adicionado: " + filho + " é filho de " + pai);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo 'arquivo.txt' não encontrado.");
            e.printStackTrace();
            return; // Encerra a execução se o arquivo não for encontrado
        }
        System.out.println("\nDados carregados com sucesso!");

        Pessoa dennis = arvore.buscarPessoa("dennis.ritchie");
        Pessoa les = arvore.buscarPessoa("les.valiant");
        Pessoa heitor = arvore.buscarPessoa("heitor.maciel");
        Pessoa oswaldo = arvore.buscarPessoa("oswaldo.veblen");
        Pessoa carlos = arvore.buscarPessoa("carlos.souza");
        Pessoa patricia = arvore.buscarPessoa("patricia.fischer");
        Pessoa joao = arvore.buscarPessoa("joao.silva");
        Pessoa bob = arvore.buscarPessoa("bob.constable");
        Pessoa michael = arvore.buscarPessoa("michael.rabin");

        System.out.println("\n--- Consultas de parentesco ---");

        if (carlos != null && bob != null) {
            System.out.println("Parentesco entre " + carlos.getNome() + " e " + bob.getNome() + ": " + arvore.Parentesco(carlos, bob));
        }
        if (heitor != null && joao != null) {
            System.out.println("Parentesco entre " + heitor.getNome() + " e " + joao.getNome() + ": " + arvore.Parentesco(heitor, joao));
        }
        if (heitor != null && carlos != null) {
            System.out.println("Parentesco entre " + heitor.getNome() + " e " + carlos.getNome() + ": " + arvore.Parentesco(heitor, carlos));
        }
        if (les != null && joao != null) {
            System.out.println("Parentesco entre " + les.getNome() + " e " + joao.getNome() + ": " + arvore.Parentesco(les, joao));
        }
        if (les != null && dennis != null) {
            System.out.println("Parentesco entre " + les.getNome() + " e " + dennis.getNome() + ": " + arvore.Parentesco(les, dennis));
        }
        if (dennis != null && les != null) {
            System.out.println("Parentesco entre " + dennis.getNome() + " e " + les.getNome() + ": " + arvore.Parentesco(dennis, les));
        }
        if (patricia != null && michael != null) {
            System.out.println("Parentesco entre " + patricia.getNome() + " e " + michael.getNome() + ": " + arvore.Parentesco(patricia, michael));
        } else {
        	System.out.println("Patricia e Michael não possuem relação");
        }
        if (les != null && oswaldo != null) {
            System.out.println("Parentesco entre " + les.getNome() + " e " + oswaldo.getNome() + ": " + arvore.Parentesco(les, oswaldo));
        }

        
    }
}