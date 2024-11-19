package BTree;

import TADStack.Stack;

public class B {
	private NodeB root;
	private int ordem;
	
	B(int ordem){
		this.ordem = ordem;
	}
	
	private NodeB cisaoPagina(NodeB pai, NodeB filho) {
		if(filho == this.root && filho.isLeaf()) {
			NodeB newRoot = new NodeB(this.ordem, false);
			NodeB newNode = new NodeB(this.ordem, true);
			
			int i = (this.ordem/2)+1;
			int j = 0;
			while(i < filho.getN()) {
				newNode.setInfos(j, filho.getInfos(i));
				newNode.setN(newNode.getN()+1);
				
				filho.setInfos(i, null);
				filho.setN(filho.getN()-1);
				j++;
				i++;
			}
			
			newRoot.setInfos(0, filho.getInfos(this.ordem/2));
			newRoot.setN(1);
			
			filho.setInfos(this.ordem, null);
			filho.setN(filho.getN()-1);
			
			newRoot.setFilhos(0, filho);
			newRoot.setFilhos(1, newNode);
			
			return newRoot;
		}
		else if(filho == this.root && !filho.isLeaf()) {
			NodeB newRoot = new NodeB(this.ordem, false);
			NodeB newNode = new NodeB(this.ordem, false);
			
			int i = (this.ordem/2)+1;
			int j = 0;
			while(i < filho.getN()) {
				newNode.setInfos(j, filho.getInfos(i));
				newNode.setFilhos(j, filho.getFilhos(i));
				newNode.setN(newNode.getN()+1);
				
				filho.setInfos(i, null);
				filho.setFilhos(i, null);
				filho.setN(filho.getN()-1);
				i++;
				j++;
			}
			newNode.setFilhos(j, filho.getFilhos(i));
			filho.setFilhos(i, null);
			
			newRoot.setInfos(0, filho.getInfos(this.ordem/2));
			newRoot.setN(1);
			newRoot.setFilhos(0, filho);
			newRoot.setFilhos(1, newNode);
			
			filho.setInfos(this.ordem/2, null);
			filho.setN(filho.getN()-1);
			
			return newRoot;
		}
		else {
			NodeB newNode = new NodeB(this.ordem, filho.isLeaf());
			int valorSobe = filho.getInfos(this.ordem/2);
			
			int i = (this.ordem/2)+1;
			int j = 0;
			while(i < filho.getN()) {
				newNode.setInfos(j, filho.getInfos(i));
				newNode.setFilhos(j, filho.getFilhos(i));
				newNode.setN(newNode.getN()+1);
				
				filho.setInfos(i, null);
				filho.setFilhos(i, null);
				filho.setN(filho.getN()-1);
				i++;
				j++;
			}
			newNode.setFilhos(j, filho.getFilhos(i));
			filho.setFilhos(i, null);
			
			int k = pai.getN() - 1;
			while(j >= 0 && valorSobe < pai.getInfos(k)) {
				pai.setInfos(k+1, pai.getInfos(k));
				k--;
			}
			pai.setInfos(k+1, valorSobe);
			pai.setN(pai.getN()+1);
			
			int l = pai.getN() - 1;
			while(j >= 0 && newNode.getInfos(0) < pai.getInfos(k)) {
				pai.setFilhos(l+1, pai.getFilhos(l));
				l--;
			}
			pai.setFilhos(l+1, newNode);
			
			filho.setInfos(this.ordem/2, null);
			filho.setN(filho.getN()-1);
			
			return pai;
		}
	}
	
	public void insert(Integer valor) {
		if(this.root.getN() == 0) {
			this.root = new NodeB(this.ordem, true);
			this.root.setInfos(0, valor);
			this.root.setN(1);
		}
		else {
			NodeB ant, atual = this.root;
			Stack<NodeB> pilha = new Stack<NodeB>();
			
			while(!atual.isLeaf()) {
				int i = 0;
				
				while(i < atual.getN() && valor > atual.getInfos(i)) {
					i++;
				}
				
				pilha.push(atual);
				atual = atual.getFilhos(i);
			}
			
			int j = atual.getN() - 1;
			while(j >= 0 && valor < atual.getInfos(j)) {
				atual.setInfos(j+1, atual.getInfos(j));
				j--;
			}
			
			atual.setInfos(j+1, valor);
			atual.setN(atual.getN()+1);
			
			while(atual != null || !pilha.isEmpty()) {
				ant = pilha.pop();
				
				if(atual.getN() == this.ordem) {
					ant = cisaoPagina(ant, atual);
				}
				atual = ant;
			}
		}
	}

	private Search maiorChave(int valor){
        if(this.root == null){
            Search maior = new Search(null, -1);

            return maior;
        }
        else{
            NodeB atual = this.root;

            while(!atual.isLeaf()){
                atual = atual.getFilhos(atual.getN() - 1);
            }

            Search maior = new Search(atual, atual.getN()-1);
            
            return maior;
            
        }
    }

    public void buscaMaior(int valor){
        Search buscaMaior;

        buscaMaior = maiorChave(valor);

        if(buscaMaior.getEndereco() == null){
            System.out.println("Árvore vazia!");
        }
        else{
            NodeB aux = buscaMaior.getEndereco();
            System.out.printf("Maior valor: %d\n", aux.getInfos(buscaMaior.getPosicao()));
        }
    }

    private Search menorChave(int valor){
        if(this.root == null){
            Search menor = new Search(null, -1);

            return menor;
        }
        else{
            NodeB atual = this.root;

            while(!atual.isLeaf()){
                atual = atual.getFilhos(0);
            }

            Search menor = new Search(atual, 0);

            return menor;
        }
    }

    public void buscaMenor(int valor){
        Search buscaMenor = menorChave(valor);

        if(buscaMenor.getEndereco() == null){
            System.out.println("Árvore vazia!");
        }
        else{
            NodeB aux = buscaMenor.getEndereco();
            System.out.printf("Maior valor: %d\n", aux.getInfos(buscaMenor.getPosicao()));
        }
    }

    public int alturaArvore(){
        if(this.root == null){
            return -1;
        }
        else{
            int cont = 0;
            NodeB atual = this.root;

            while(!atual.isLeaf()){
                atual = atual.getFilhos(0);
                cont++;
            }

            return cont;
        }
    }

    private Search buscaValor(int valor){
        if(this.root == null){
            Search busca = new Search(null, -1);

            return busca;
        }
        else{
            NodeB atual = this.root;
        }
    }
}
