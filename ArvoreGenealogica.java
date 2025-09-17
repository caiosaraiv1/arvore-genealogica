/*
 * Classe ArvoreGenealogica: representa a árvore binária da família.
 * Responsável por gerenciar o ancestral mais antigo (raiz),
 * adicionar relacionamentos e realizar consultas de parentesco
 * entre as pessoas da árvore.
 */

import java.util.Map;
import java.util.HashMap;

public class ArvoreGenealogica {
	
	Pessoa ancestralComum;
	
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
	
	public Pessoa criarNo(String nomePai, String nomeFilho){ // Tem que chamar no Main

		Map<String, Pessoa> map = new HashMap<>();

		Pessoa pai = map.get(nomePai); // Busca o pai no dicionario
		if(pai == null){ // Se o pai nao existir cria ele e adiciona
			pai = new Pessoa(nomePai);
			map.put(nomePai, pai);
		}

		Pessoa filho = map.get(nomeFilho); // Busca o filho no dicionario
		if(filho == null){ // Se o filho nao existir cria ele e adiciona
			filho = new Pessoa(nomeFilho);
			map.put(nomeFilho, filho);
		}
	}

	private Pessoa buscarPessoaRec(Pessoa atual, String nome) {		
		if (atual == null) return null;
		if (atual.getNome().equals(nome)) return atual;
		
		Pessoa esquerda = buscarPessoaRec(atual.getFilhoEquerda(), nome);
		if (esquerda != null) return esquerda;
		
		return buscarPessoaRec(atual.getFilhoDireita(), nome);
	}

}
		
	

