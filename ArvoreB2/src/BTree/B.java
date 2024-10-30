package BTree;

public class B {
	private NodeB root;
	private int ordem;
	
	B(int ordem){
		this.ordem = ordem;
	}
	
	private void cisaoDePagina(NodeB ant, NodeB atual) {
		int valorSobe = atual.getInfos((this.ordem/2));
		
		if(atual == this.root) {
			
		}
		else if(atual.isLeaf() == false){
			ant.setInfos(ant.getN(), valorSobe);
			ant.setN(ant.getN()+1);
			
			NodeB novoNo = new NodeB(ordem, false);
		}
	}
	
	public void insert(Integer valor) {
		if(this.root == null) {
			this.root = new NodeB(ordem, true);
			this.root.setInfos(0, valor);
			this.root.setN(1);
		}
		else {
			NodeB ant, atual = this.root;
			
			while(!atual.isLeaf()) {
				if(atual.getN() == ordem) {
					//cisaoDePagina(ant, atual);
				}
				else {
					int i = 0;
					
					while(i < atual.getN()) {
						if(valor < atual.getInfos(i)) {
							ant = atual;
							atual = atual.getFilhos(i);
							break;
						}
						i++;
					}
					if(valor > atual.getInfos(i)) {
						ant = atual;
						atual = atual.getFilhos(i+1);
					}
				}
			}
			
			int j = atual.getN();
			
			while(j >= 0 && valor > atual.getInfos(j)) {
				atual.setInfos(j+1, atual.getInfos(j));
				j--;
			}
			
			atual.setInfos(j+1, valor);
			atual.setN(atual.getN()+1);
			
			if(atual.getN() == ordem) {
				//cisaoDePagina(ant, atual);
			}
		}
	}
}
