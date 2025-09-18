/*
 * Classe Pessoa: representa um nó da árvore genealógica.
 * Cada pessoa possui um nome, referência para o pai e até dois filhos
 * (esquerda e direita), permitindo a construção de uma árvore binária.
 */
public class Pessoa {

	private String nome;
	private Pessoa pai;
	private Pessoa filhoEquerda;
	private Pessoa filhoDireita;
	
	public Pessoa(String nome) {
		this.nome = nome;
		this.pai = null;
		this.filhoEquerda = null;
		this.filhoDireita = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa getPai() {
		return pai;
	}

	public void setPai(Pessoa pai) {
		this.pai = pai;
	}

	public Pessoa getFilhoEquerda() {
		return filhoEquerda;
	}

	public void setFilhoEquerda(Pessoa filhoEquerda) {
		this.filhoEquerda = filhoEquerda;
	}

	public Pessoa getFilhoDireita() {
		return filhoDireita;
	}

	public void setFilhoDireita(Pessoa filhoDireita) {
		this.filhoDireita = filhoDireita;
	}
	
	@Override
	public String toString() {
	    return this.nome;
	}

}
