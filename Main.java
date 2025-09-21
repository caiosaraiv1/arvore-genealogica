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

        // --- 2. Realizando buscas e consultas ---
        System.out.println("\n--- 2. Realizando buscas e consultas ---");

        Pessoa dennis = arvore.buscarPessoa("dennis.ritchie");
        Pessoa les = arvore.buscarPessoa("les.valiant");
        Pessoa heitor = arvore.buscarPessoa("heitor.maciel");
        Pessoa oswaldo = arvore.buscarPessoa("oswaldo.veblen");
        Pessoa carlos = arvore.buscarPessoa("carlos.souza");
        Pessoa patricia = arvore.buscarPessoa("patricia.fischer");

        System.out.println("Buscando 'carlos.souza': " + (carlos != null ? "Encontrado." : "Não encontrado."));
        System.out.println("Buscando 'pessoa.inexistente': " + (arvore.buscarPessoa("pessoa.inexistente") != null ? "Encontrado." : "Não encontrado."));

        // --- 3. Demonstração de funcionalidades da árvore ---
        System.out.println("\n--- 3. Demonstração de funcionalidades da árvore ---");

        // Ancestral Comum
        if (dennis != null && les != null) {
            Pessoa ancestralComum = arvore.ancestralComum(dennis, les);
            System.out.println("O ancestral comum entre " + dennis.getNome() + " e " + les.getNome() + " é: " + (ancestralComum != null ? ancestralComum.getNome() : "Não encontrado."));
        }

        // Nível na árvore
        if (dennis != null) {
            System.out.println("Nível de " + dennis.getNome() + ": " + arvore.nivel(dennis));
        }
        if (oswaldo != null) {
            System.out.println("Nível de " + oswaldo.getNome() + ": " + arvore.nivel(oswaldo));
        }

        // Distância entre pessoas
        /*
        if (heitor != null && oswaldo != null) {
            System.out.println("Distância entre " + heitor.getNome() + " e " + oswaldo.getNome() + ": " + arvore.distancia(heitor, oswaldo));
        }
        if (carlos != null && oswaldo != null) {
            System.out.println("Distância entre " + carlos.getNome() + " e " + oswaldo.getNome() + ": " + arvore.distancia(carlos, oswaldo));
        }
		*/
        // --- Consultas de Parentesco ---
        System.out.println("\n--- Consultas de Parentesco ---");

        // Parentesco de mesmo nível (irmãos)
        if (carlos != null && patricia != null) {
            System.out.println("Parentesco entre " + carlos.getNome() + " e " + patricia.getNome() + ": " + arvore.Parentesco(carlos, patricia));
        }

        // Parentesco de avô e neto
        if (carlos != null && oswaldo != null) {
            System.out.println("Parentesco entre " + oswaldo.getNome() + " e " + carlos.getNome() + ": " + arvore.Parentesco(oswaldo, carlos));
        }

        // Parentesco de primo
        if (heitor != null && carlos != null) {
            System.out.println("Parentesco entre " + heitor.getNome() + " e " + carlos.getNome() + ": " + arvore.Parentesco(heitor, carlos));
        }

        // Casos com pessoas não relacionadas diretamente
        if (heitor != null && patricia != null) {
            System.out.println("Parentesco entre " + heitor.getNome() + " e " + patricia.getNome() + ": " + arvore.Parentesco(heitor, patricia));
        }

        // Parentesco com o próprio ancestral
        if (dennis != null && oswaldo != null) {
            System.out.println("Parentesco entre " + dennis.getNome() + " e " + oswaldo.getNome() + ": " + arvore.Parentesco(dennis, oswaldo));
        }

        // --- 4. Exibição da árvore (percursos) ---
        System.out.println("\n--- 4. Exibição da árvore (percursos) ---");
        System.out.println("Percurso In-Ordem (Filho Esquerda -> Pai -> Filho Direita):");
        arvore.mostraInOrdem();

        System.out.println("\nPercurso Pré-Ordem (Pai -> Filho Esquerda -> Filho Direita):");
        arvore.mostraPreOrdem();
    }
}