/*
 * Classe ArvoreGenealogica: representa a árvore binária da família.
 * Responsável por gerenciar o ancestral mais antigo (raiz),
 * adicionar relacionamentos e realizar consultas de parentesco
 * entre as pessoas da árvore.
 */
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
	
	private Pessoa buscarPessoaRec(Pessoa atual, String nome) {		
		if (atual == null) return null;
		if (atual.getNome().equals(nome)) return atual;
		
		Pessoa esquerda = buscarPessoaRec(atual.getFilhoEquerda(), nome);
		if (esquerda != null) return esquerda;
		
		return buscarPessoaRec(atual.getFilhoDireita(), nome);
	}

}
		
	

