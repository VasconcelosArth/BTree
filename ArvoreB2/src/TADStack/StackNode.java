package TADStack;

class StackNode <T> {
    private T info;
    private StackNode<T> prox;
    
    StackNode (T value) {
        this.info = value;
    }
    
    void setInfo (T value) {
        this.info = value;
    }
    
    T getInfo() {
        return this.info;
    }
    
    void setProx (StackNode<T> novoProx) {
        this.prox = novoProx;
    }
    
    StackNode<T> getProx() {
        return this.prox;
    }
}