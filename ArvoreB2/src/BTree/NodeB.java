package BTree;

class NodeB {
	private int n;
	private Integer[] infos;
	private NodeB[] filhos;
	private boolean leaf;
	
	NodeB(int M, boolean leaf){
		this.leaf = leaf;
		
		this.n = 0;
		
		this.infos = new Integer[M];
		this.filhos = new NodeB[M];
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Integer getInfos(int pos) {
		return infos[pos];
	}

	public void setInfos(int pos, Integer info) {
		this.infos[pos] = info;
	}

	public NodeB getFilhos(int pos) {
		return filhos[pos];
	}

	public void setFilhos(int pos, NodeB filhos) {
		this.filhos[pos] = filhos;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
}