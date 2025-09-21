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

    public void criarNo(String nomePai, String nomeFilho) {
        Pessoa pai = map.get(nomePai);
        if (pai == null) {
            pai = new Pessoa(nomePai);
            if (this.ancestralComum == null) {
                this.ancestralComum = pai;
            }
            map.put(nomePai, pai);
        }

        Pessoa filho = map.get(nomeFilho);
        if (filho == null) {
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

        throw new Exception("Pai já tem dois filhos");
    }

    private void mostraInOrdemPrivate(Pessoa p) {
        if (p != null) {
            mostraInOrdemPrivate(p.getFilhoEquerda());
            System.out.println(p.getNome());
            mostraInOrdemPrivate(p.getFilhoDireita());
        }
    }

    public void mostraInOrdem() {
        mostraInOrdemPrivate(ancestralComum);
    }

    private void mostraPreOrdemPrivate(Pessoa p) {
        Stack<Pessoa> pilha = new Stack<>();
        Pessoa atual = p;
        while (atual != null || !pilha.isEmpty()) {
            if (atual != null) {
                System.out.println(atual.getNome());
                pilha.push(atual);
                atual = atual.getFilhoEquerda();
            } else {
                atual = pilha.pop();
                atual = atual.getFilhoDireita();
            }
        }
    }

    public void mostraPreOrdem() {
        mostraPreOrdemPrivate(ancestralComum);
    }

    public Pessoa ancestralComum(Pessoa a, Pessoa b) {
        if (a == null || b == null) return null; //se pessoa invalida
        if (a.equals(b)) return a; //se mesmo nome

        Pessoa auxA = a;
        while (auxA != null) { //anda A de um em um enquanto procura todos de B
            Pessoa auxB = b; 
            while (auxB != null) {
                if (auxA.equals(auxB)) {
                    return auxA;
                }
                auxB = auxB.getPai();
            }
            auxA = auxA.getPai();
        }
        return null;
    }
    
    //nivel normal p/ raiz
    public int nivel(Pessoa a) {
        if (a == null) {
            return -1;
        }
        int contador = 0;
        Pessoa aux = a;
        while (aux.getPai() != null) {
            aux = aux.getPai();
            contador++;
        }
        return contador;
    }

    //nivel em relação a uma pessoa em específico
    public int nivel(Pessoa p, Pessoa ancestral) {
        if (p == null || ancestral == null) { 
            return -1;
        }
        int contador = 0;
        Pessoa aux = p;
        while (aux != null && !aux.equals(ancestral)) {
            aux = aux.getPai();
            contador++;
        }
        if (aux == null) {
            return -1;
        }
        return contador;
    }

    private String geraTatara(int dist) {
        if (dist <= 0) return "mesma pessoa";
        if (dist == 1) return "Pai";
        if (dist == 2) return "Avô";
        if (dist == 3) return "Bisavô";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dist - 3; i++) {
            sb.append("tatara");
        }
        sb.append("neto");
        return sb.toString();
    }
    
    public String Parentesco(Pessoa a, Pessoa b) {
        if (a == null || b == null) return "Inválido.";
        if (a.equals(b)) return "Mesma pessoa";
    
        Pessoa ac = ancestralComum(a, b);
        if (ac == null) return "sem relacao";
    
        int nivelA = nivel(a);
        int nivelB = nivel(b);
    
        //se pai
        if (b.getPai() != null && b.getPai().equals(a)) {
            return "pai";
        }
    
        //se irmãos
        if (a.getPai() != null && a.getPai().equals(b.getPai())) {
            return "irmao";
        }
    
        //se relação direta (tataras)
        if (ac.equals(a) || ac.equals(b)) {
            if (a.equals(ac)) {
                return geraTatara(nivelB - nivelA);
            } else {
                return geraTatara(nivelA - nivelB);
            }
        }
    
        //se primos
        int nivelAC = nivel(ac);
        int k = nivelA - nivelAC - 1;
        int grau = Math.abs(nivelA - nivelB);
    
        return "primo-" + k + " em grau " + grau;
    }
}