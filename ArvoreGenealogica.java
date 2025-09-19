/*
 * Classe ArvoreGenealogica: representa a árvore binária da família.
 * Responsável por gerenciar o ancestral mais antigo (raiz),
 * adicionar relacionamentos e realizar consultas de parentesco
 * entre as pessoas da árvore.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ArvoreGenealogica {

    Pessoa ancestralComum;
    Map<String, Pessoa> map = new HashMap<>();

    ;
	
	public ArvoreGenealogica() {
        this.ancestralComum = null;
    }

    public ArvoreGenealogica(Pessoa p) {
        this.ancestralComum = p;
    }

    public Pessoa getRaiz() {
        return ancestralComum;
    }

    public void setRaiz(Pessoa raiz) {
        this.ancestralComum = raiz;
    }

    public Pessoa buscarPessoa(String nome) {
        return buscarPessoaRec(this.ancestralComum, nome);
    }

    public void criarNo(String nomePai, String nomeFilho) { // Tem que chamar no Main

        Pessoa pai = map.get(nomePai); // Busca o pai no dicionario
        if (pai == null) { // Se o pai nao existir cria ele e adiciona
            pai = new Pessoa(nomePai);
            map.put(nomePai, pai);
        }

        Pessoa filho = map.get(nomeFilho); // Busca o filho no dicionario
        if (filho == null) { // Se o filho nao existir cria ele e adiciona
            filho = new Pessoa(nomeFilho);
            map.put(nomeFilho, filho);
        }

        try {
            adicionarFilho(pai, filho);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Pessoa buscarPessoaRec(Pessoa atual, String nome) {
        if (atual == null) {
            return null;
        }
        if (atual.getNome().equals(nome)) {
            return atual;
        }

        Pessoa esquerda = buscarPessoaRec(atual.getFilhoEquerda(), nome);
        if (esquerda != null) {
            return esquerda;
        }

        return buscarPessoaRec(atual.getFilhoDireita(), nome);
    }

    private void adicionarFilho(Pessoa pai, Pessoa filho) throws Exception {
        if (ancestralComum == null) {
            ancestralComum = pai;
            pai.setFilhoEquerda(filho);
            filho.setPai(pai);
            return;
        }

        if (pai.getFilhoEquerda() == null) {
            pai.setFilhoEquerda(filho);
            filho.setPai(pai);
            return;
        }

        if (pai.getFilhoDireita() == null) {
            pai.setFilhoDireita(filho);
            filho.setPai(pai);
            return;
        }

        throw new Exception("Pai ja com dois filhos");
    }

    private void mostraInOrdemPrivate(Pessoa p) { //Recursivo
        if (p != null) {
            mostraInOrdemPrivate(p.getFilhoEquerda());
            System.out.println(p.getNome());
            mostraInOrdemPrivate(p.getFilhoDireita());
        }
    }

	public void mostraInOrdem(){
		mostraInOrdemPrivate(ancestralComum);
	}

    private void mostraPreOrdemPrivate(Pessoa p) { //Iterativo
        Stack<Pessoa> pilha = new Stack<>();
        while(p != null || !pilha.isEmpty()) {
            if (p != null) {
                System.out.println(p.getNome());
                pilha.push(p);
                p = p.getFilhoEquerda();
            } else {
                p = (Pessoa) pilha.pop();
                p = p.getFilhoDireita();
            }
        }
    }

	public void mostraPreOrdem(){
		mostraPreOrdemPrivate(ancestralComum);
	}

	public Pessoa ancestralComum(Pessoa a, Pessoa b) {
		//Casos base
		if(a == null || b == null) return null;
		if(a == b) return null;
		if(a.getPai() == b.getPai()) return a.getPai();

		//Recursivo em b
		Pessoa ancestal = ancestralComum(a, b.getPai());
		if(ancestal != null) return ancestal;
		//a anda um pra frente
		return ancestralComum(a.getPai(), b);
	}

	public int nivel(Pessoa a){
		if(a == this.ancestralComum) return 0;
		if(a == null) return -1;
		Pessoa aux = a;
		int contador = -1;
		while(aux != null){
			aux = aux.getPai();
			contador++;

		}
		return contador;
	}

    private static final int ERRO_PARAMETRO = -1;
    private static final int MESMA_PESSOA = -2;

	public int distancia(Pessoa a, Pessoa b){ // verificar qual o mais perto da raiz para definir quem é o "raiz"
		if(a == null || b == null) return ERRO_PARAMETRO;
		if(a == b) return MESMA_PESSOA;
		
		Pessoa raiz, aux;
		if(nivel(a) > nivel(b)){
			raiz = b;
			aux = a;
		} else {
			raiz = a;
			aux = b;
		}
		int contador = 0;
		while(aux.getPai() != raiz){
			aux = aux.getPai();
			contador++;
		}
		return contador;
	}

    private String geraTatara(int dist){
        if(dist == 0) return "pai";
        if(dist == 1) return "avô";

        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < dist - 1; i++){
            sb.append("tatara");
        }
        sb.append("avô");
        return sb.toString();
    }

	public String Parentesco(Pessoa a, Pessoa b){
		//casos base
		if(a == null || b == null) return "Invalido.";
		if(a == b) return "Mesma pessoa";

		Pessoa ac = ancestralComum(a, b);
		int grau = Math.abs(nivel(a) - nivel(b));
		
		if(ac == a || ac == b){ //ancestral em comum é a ou b -> tataraalgo
			int distAB = distancia(a, b);
			return geraTatara(distAB);
		}
        /*
        if (distancia(a, ac) > distancia(b, ac)){
			k = distancia(b, ac);
		} else {
			k = distancia(a, ac);
		}
        */
		// int k = Math.abs(distancia(a, ac) - distancia(b, ac));
        int k = Math.min(distancia(a, ac), distancia(b, ac));
		return ("Primo: " + k + " grau " + grau);
	}
}


