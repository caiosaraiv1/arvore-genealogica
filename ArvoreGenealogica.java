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

	
}
